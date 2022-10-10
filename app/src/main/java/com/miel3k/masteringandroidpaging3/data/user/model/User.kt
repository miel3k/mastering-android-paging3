package com.miel3k.masteringandroidpaging3.data.user.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.realm.RealmObject

/**
 * Created by miel3k on 18/09/2022
 */
@Entity(tableName = "users")
open class User(
    @io.realm.annotations.PrimaryKey
    @PrimaryKey var id: Int = 0,
    var login: String = "",
    var avatar_url: String = ""
) : RealmObject()