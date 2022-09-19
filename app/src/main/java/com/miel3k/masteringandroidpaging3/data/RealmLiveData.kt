package com.miel3k.masteringandroidpaging3.data

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Created by jmielczarek on 18/09/2022
 */
class RealmLiveData<T : RealmModel>(private val results: RealmResults<T>) :
    LiveData<RealmResults<T>>() {
    private val listener = RealmChangeListener<RealmResults<T>> { value = it }

    override fun onActive() {
        results.addChangeListener(listener)
        value = results
    }

    override fun onInactive() {
        results.removeChangeListener(listener)
    }
}

fun <T : RealmModel> RealmResults<T>.toLiveData() = RealmLiveData(this)

fun <T : Any, K : RealmObject> RealmResults<K>.asList(extractor: (K) -> Iterable<T>?): List<T> =
    realm.copyFromRealm(this)?.mapNotNull(extractor)?.flatten() ?: emptyList()

fun <K : RealmObject> RealmResults<K>.asList(): List<K> = realm.copyFromRealm(this)