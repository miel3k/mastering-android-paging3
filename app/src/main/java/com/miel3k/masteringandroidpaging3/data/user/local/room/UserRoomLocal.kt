package com.miel3k.masteringandroidpaging3.data.user.local.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.model.User
import javax.inject.Inject

/**
 * Created by miel3k on 18/09/2022
 */
class UserRoomLocal @Inject constructor(private val userDao: UserDao) : UserLocalDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> =
        userDao.getUsers().map { Result.Success(it) }

    override suspend fun saveUsers(users: List<User>) {
        userDao.insertUsers(users)
    }

    override fun deleteUsers() {
        userDao.deleteUsers()
    }

    override fun getUsersPagingSource(): PagingSource<Int, User> {
        return userDao.getUsersPagingSource()
    }
}