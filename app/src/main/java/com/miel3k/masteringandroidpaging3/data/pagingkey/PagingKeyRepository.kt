package com.miel3k.masteringandroidpaging3.data.pagingkey

import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by jmielczarek on 19/09/2022
 */
class PagingKeyRepository(
    private val local: PagingKeyLocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : PagingKeyDataSource {

    override suspend fun getPagingKey(pagingId: String): PagingKey? {
        return withContext(dispatcher) { local.getPagingKey(pagingId) }
    }

    override suspend fun savePagingKey(pagingKey: PagingKey) {
        withContext(dispatcher) { local.savePagingKey(pagingKey) }
    }
}