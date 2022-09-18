package com.miel3k.masteringandroidpaging3.data.users.remote

import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.users.model.User
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
class UsersRemote @Inject constructor(private val usersApi: UsersApi) : UsersRemoteDataSource {

    override suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>> {
        val response = usersApi.loadUsers(since, perPage)
        return if (response.isSuccessful) {
            Result.Success(response.body().orEmpty())
        } else {
            Result.Error(Exception())
        }
    }
}