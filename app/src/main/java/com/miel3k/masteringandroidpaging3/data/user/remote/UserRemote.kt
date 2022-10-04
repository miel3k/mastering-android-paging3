package com.miel3k.masteringandroidpaging3.data.user.remote

import com.miel3k.masteringandroidpaging3.data.Result
import com.miel3k.masteringandroidpaging3.data.user.model.User
import java.io.IOException
import javax.inject.Inject

/**
 * Created by jmielczarek on 18/09/2022
 */
class UserRemote @Inject constructor(private val userApi: UserApi) : UserRemoteDataSource {

    override suspend fun loadUsers(since: Int, perPage: Int): Result<List<User>> {
        return try {
            val response = userApi.loadUsers(since, perPage)
            if (response.isSuccessful) {
                Result.Success(response.body().orEmpty())
            } else {
                Result.Error(Exception())
            }
        } catch (e: IOException) {
            Result.Error(e)
        }
    }
}