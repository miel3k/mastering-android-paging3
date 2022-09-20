package com.miel3k.masteringandroidpaging3.data.pagingkey.local.room

import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import javax.inject.Inject

/**
 * Created by jmielczarek on 20/09/2022
 */
class PagingKeyRoomLocal @Inject constructor(private val database: MasteringPagingRoomDatabase) :
    PagingKeyLocalDataSource {

    override fun getPagingKey(pagingId: String): PagingKey? =
        database.pagingKeyDao().getPagingKey(pagingId)

    override fun savePagingKey(pagingKey: PagingKey) {
        database.pagingKeyDao().insertPagingKey(pagingKey)
    }
}