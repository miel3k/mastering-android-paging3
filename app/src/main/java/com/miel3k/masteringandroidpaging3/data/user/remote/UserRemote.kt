package com.miel3k.masteringandroidpaging3.data.user.remote

import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.model.User
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
class UserRemote @Inject constructor(private val userApi: UserApi) : UserRemoteDataSource {

    override suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>> {
        val response = userApi.loadUsers(since, perPage)
        return if (response.isSuccessful) {
            Result.Success(response.body().orEmpty())
        } else {
            Result.Error(Exception())
        }
    }
}