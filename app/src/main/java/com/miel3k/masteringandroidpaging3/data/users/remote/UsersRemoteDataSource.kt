package com.miel3k.masteringandroidpaging3.data.users.remote

import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.model.User

/**
 * Created by jmielczarek on 18/09/2022
 */
interface UsersRemoteDataSource {
    suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>>
}