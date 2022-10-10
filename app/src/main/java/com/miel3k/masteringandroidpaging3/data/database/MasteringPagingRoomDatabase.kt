package com.miel3k.masteringandroidpaging3.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.miel3k.masteringandroidpaging3.data.pagingkey.local.room.PagingKeyDao
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey
import com.miel3k.masteringandroidpaging3.data.user.local.room.UserDao
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by miel3k on 18/09/2022
 */
@Database(entities = [User::class, PagingKey::class], version = 1, exportSchema = false)
abstract class MasteringPagingRoomDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun pagingKeyDao(): PagingKeyDao
}