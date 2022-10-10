package com.miel3k.masteringandroidpaging3.data.user.local

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by miel3k on 18/09/2022
 */
interface UserLocalDataSource {
    fun observeUsers(): LiveData<Result<List<User>>>
    suspend fun saveUsers(users: List<User>)
    fun deleteUsers()
    fun getUsersPagingSource(): PagingSource<Int, User>
}