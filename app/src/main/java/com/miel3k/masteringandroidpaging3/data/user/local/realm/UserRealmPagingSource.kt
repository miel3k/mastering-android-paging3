package com.miel3k.masteringandroidpaging3.data.user.local.realm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.miel3k.masteringandroidpaging3.data.user.model.User
import io.realm.RealmChangeListener
import io.realm.RealmResults

/**
 * Created by jmielczarek on 19/09/2022
 */
class UserRealmPagingSource(private val userResults: RealmResults<User>) :
    PagingSource<Int, User>() {

    private val wrapperListener = RealmChangeListener<RealmResults<User>> { invalidate() }

    init {
        userResults.addChangeListener(wrapperListener)
        registerInvalidatedCallback {
            userResults.removeChangeListener(wrapperListener)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: INITIAL_PAGE
        val pageSize = params.loadSize
        val documents = userResults
        val pageDocuments = extractPage(documents, pageIndex, pageSize)
        return generateResult(pageIndex, pageDocuments)
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private fun extractPage(
        objects: List<User>,
        pageIndex: Int,
        pageSize: Int
    ): List<User> {
        val startOffset = pageIndex * pageSize
        val pageObjects = objects.drop(startOffset)
        return if (pageObjects.isEmpty()) {
            pageObjects
        } else {
            val endOffset = if (pageSize > pageObjects.size) pageObjects.size else pageSize
            pageObjects.subList(0, endOffset)
        }
    }

    private fun generateResult(
        pageIndex: Int,
        objects: List<User>
    ): LoadResult<Int, User> {
        val prevKey = if (pageIndex == 0) null else pageIndex - 1
        val nextKey = if (objects.isEmpty()) null else pageIndex + 1
        return LoadResult.Page(data = objects, prevKey = prevKey, nextKey = nextKey)
    }

    private companion object {
        const val INITIAL_PAGE = 0
    }
}