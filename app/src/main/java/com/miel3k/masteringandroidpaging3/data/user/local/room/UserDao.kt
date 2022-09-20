package com.miel3k.masteringandroidpaging3.data.user.local.room

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by jmielczarek on 18/09/2022
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(users: List<User>)

    @Query("DELETE FROM users")
    fun deleteUsers()

    @Query("SELECT * FROM users")
    fun getUsersPagingSource(): PagingSource<Int, User>
}