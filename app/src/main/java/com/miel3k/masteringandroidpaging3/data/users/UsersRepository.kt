package com.miel3k.masteringandroidpaging3.data.users

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.local.UsersLocalDataSource
import com.miel3k.masteringandroidpaging3.data.users.model.User
import com.miel3k.masteringandroidpaging3.data.users.remote.UsersRemoteDataSource

/**
 * Created by jmielczarek on 18/09/2022
 */
class UsersRepository(
    private val local: UsersLocalDataSource,
    private val remote: UsersRemoteDataSource
) : UsersDataSource {

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