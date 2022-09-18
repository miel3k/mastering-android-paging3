package com.miel3k.masteringandroidpaging3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miel3k.masteringandroidpaging3.data.users.model.User
import com.miel3k.masteringandroidpaging3.data.users.local.room.UsersDao

/**
 * Created by jmielczarek on 18/09/2022
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MasteringPagingRoomDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}