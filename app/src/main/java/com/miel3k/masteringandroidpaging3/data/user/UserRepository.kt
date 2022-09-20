package com.miel3k.masteringandroidpaging3.data.user

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.model.User
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemoteDataSource

/**
 * Created by jmielczarek on 18/09/2022
 */
class UserRepository(
    private val local: UserLocalDataSource,
    private val remote: UserRemoteDataSource
) : UserDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> {
        return local.observeUsers()
    }

    override suspend fun saveUsers(users: List<User>) {
        local.saveUsers(users)
    }

    override suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>> {
        val result = remote.loadUsers(since, perPage)
        if (result is Result.Success) {
            saveUsers(result.data)
        }
        return result
    }

    override fun deleteUsers() {
        local.deleteUsers()
    }

    override fun getUsersPagingSource(): PagingSource<Int, User> {
        return local.getUsersPagingSource()
    }
}