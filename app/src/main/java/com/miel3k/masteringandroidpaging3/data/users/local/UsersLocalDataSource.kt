package com.miel3k.masteringandroidpaging3.data.users.local

import androidx.lifecycle.LiveData
import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.model.User

/**
 * Created by jmielczarek on 18/09/2022
 */
interface UsersLocalDataSource {
    fun observeUsers(): LiveData<Result<List<User>>>
    suspend fun saveUsers(users: List<User>)
}