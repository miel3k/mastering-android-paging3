package com.miel3k.masteringandroidpaging3.data.pagingkey.model

import io.realm.RealmObject
import java.util.*

/**
 * Created by jmielczarek on 19/09/2022
 */
open class PagingKey(
    @io.realm.annotations.PrimaryKey
    @androidx.room.PrimaryKey
    var id: String = UUID.randomUUID().toString(),
    var nextPageKey: Int = 0
) : RealmObject() {

    companion object {
        const val ID = "id"
    }
}