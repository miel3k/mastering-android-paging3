package com.miel3k.masteringandroidpaging3.data.user.remote

import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.model.User

/**
 * Created by jmielczarek on 18/09/2022
 */
interface UserRemoteDataSource {
    suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>>
}