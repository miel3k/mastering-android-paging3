package com.miel3k.masteringandroidpaging3.data.user

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.model.User
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Created by miel3k on 18/09/2022
 */
class UserRepository(
    private val local: UserLocalDataSource,
    private val remote: UserRemoteDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : UserDataSource {

    override fun observeUsers(): LiveData<Result<List<User>>> {
        return local.observeUsers()
    }

    override suspend fun saveUsers(users: List<User>) {
        withContext(dispatcher) { local.saveUsers(users) }
    }

    override suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>> {
        return remote.loadUsers(since, perPage)
    }

    override suspend fun deleteUsers() {
        withContext(dispatcher) { local.deleteUsers() }
    }

    override fun getUsersPagingSource(): PagingSource<Int, User> {
        return local.getUsersPagingSource()
    }
}