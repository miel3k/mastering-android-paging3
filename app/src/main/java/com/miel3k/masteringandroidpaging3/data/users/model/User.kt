package com.miel3k.masteringandroidpaging3.data.users.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.realm.RealmObject
import java.util.*

/**
 * Created by jmielczarek on 18/09/2022
 */
@Entity(tableName = "users")
open class User(
    @io.realm.annotations.PrimaryKey
    @PrimaryKey var id: String = UUID.randomUUID().toString(),
    var name: String = ""
) : RealmObject()