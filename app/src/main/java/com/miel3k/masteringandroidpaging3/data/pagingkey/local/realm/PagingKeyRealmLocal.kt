package com.miel3k.masteringandroidpaging3.data.pagingkey.local.realm

import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import io.realm.Realm
import io.realm.kotlin.where
import javax.inject.Inject
import javax.inject.Provider

/**
 * Created by miel3k on 20/09/2022
 */
class PagingKeyRealmLocal @Inject constructor(private val realm: Provider<Realm>) :
    PagingKeyLocalDataSource {

    override fun getPagingKey(pagingId: String): PagingKey? {
        val realm = realm.get()
        return realm.where<PagingKey>()
            .equalTo(PagingKey.ID, pagingId)
            .findFirst()
            ?.let { realm.copyFromRealm(it) }
    }

    override fun savePagingKey(pagingKey: PagingKey) {
        realm.get().executeTransaction {
            it.insertOrUpdate(pagingKey)
        }
    }
}