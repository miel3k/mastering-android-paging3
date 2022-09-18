package com.miel3k.masteringandroidpaging3.data.users.local.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.local.UsersLocalDataSource
import com.miel3k.masteringandroidpaging3.data.users.model.User
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
class UsersRoomLocal @Inject constructor(private val usersDao: UsersDao) : UsersLocalDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> =
        usersDao.getUsers().map { Result.Success(it) }

    override suspend fun saveUsers(users: List<User>) {
        usersDao.insertUsers(users)
    }
}