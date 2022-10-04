package com.miel3k.masteringandroidpaging3.data.user.local.realm

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.miel3k.masteringandroidpaging3.data.user.model.User
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmResults
import io.realm.kotlin.where

/**
 * Created by jmielczarek on 19/09/2022
 */
class UserRealmPagingSource(realm: Realm) : PagingSource<Int, User>() {

    private val userRealmResults = realm.where<User>().findAll()
    private val userResultsChangeListener = RealmChangeListener<RealmResults<User>> { invalidate() }

    init {
        userRealmResults.addChangeListener(userResultsChangeListener)
        registerInvalidatedCallback {
            userRealmResults.removeChangeListener(userResultsChangeListener)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: INITIAL_PAGE
        val pageSize = params.loadSize
        val startOffset = pageIndex * pageSize
        val pageObjects = userRealmResults.drop(startOffset)
        val pageUsers = if (pageObjects.isEmpty()) {
            pageObjects
        } else {
            val endOffset = if (pageSize > pageObjects.size) pageObjects.size else pageSize
            pageObjects.subList(0, endOffset)
        }
        val prevKey = if (pageIndex == 0) null else pageIndex - 1
        val nextKey = if (pageUsers.isEmpty()) null else pageIndex + 1
        return LoadResult.Page(data = pageUsers, prevKey = prevKey, nextKey = nextKey)
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    private companion object {
        const val INITIAL_PAGE = 0
    }
}