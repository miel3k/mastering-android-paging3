package com.miel3k.masteringandroidpaging3.data.pagingkey.local.realm

import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import io.realm.Realm
import io.realm.kotlin.where
import javax.inject.Inject

/**
 * Created by jmielczarek on 20/09/2022
 */
class PagingKeyRealmLocal @Inject constructor(private val realm: Realm) : PagingKeyLocalDataSource {

    override fun getPagingKey(pagingId: String): PagingKey? =
        realm.where<PagingKey>().equalTo(PagingKey.ID, pagingId).findFirst()

    override fun savePagingKey(pagingKey: PagingKey) {
        realm.executeTransaction {
            it.insertOrUpdate(pagingKey)
        }
    }
}