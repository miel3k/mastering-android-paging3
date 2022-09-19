package com.miel3k.masteringandroidpaging3.data.pagingkey

import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey

/**
 * Created by jmielczarek on 19/09/2022
 */
class PagingKeyRepository(private val local: PagingKeyLocalDataSource) : PagingKeyDataSource {

    override fun getPagingKey(pagingId: String): PagingKey? {
        return local.getPagingKey(pagingId)
    }

    override fun savePagingKey(pagingKey: PagingKey) {
        local.savePagingKey(pagingKey)
    }
}