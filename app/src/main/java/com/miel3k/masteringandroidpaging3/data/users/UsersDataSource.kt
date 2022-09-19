package com.miel3k.masteringandroidpaging3.data.users

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.model.User

/**
 * Created by jmielczarek on 18/09/2022
 */
interface UsersDataSource {
    fun observeUsers(): LiveData<Result<List<User>>>
    suspend fun saveUsers(users: List<User>)
    suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>>
    fun deleteUsers()
    fun getUsersPagingSource(): PagingSource<Int, User>
}