package com.miel3k.masteringandroidpaging3.data.pagingkey.model

import androidx.room.Entity
import io.realm.RealmObject
import java.util.*

/**
 * Created by miel3k on 19/09/2022
 */
@Entity(tableName = "pagingKey")
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