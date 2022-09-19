package com.miel3k.masteringandroidpaging3.data.pagingkey

import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey

/**
 * Created by jmielczarek on 19/09/2022
 */
interface PagingKeyDataSource {
    fun getPagingKey(pagingId: String): PagingKey?
    fun savePagingKey(pagingKey: PagingKey)
}