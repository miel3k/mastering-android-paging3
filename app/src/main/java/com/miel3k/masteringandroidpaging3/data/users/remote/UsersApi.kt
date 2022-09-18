package com.miel3k.masteringandroidpaging3.data.users.remote

import com.miel3k.masteringandroidpaging3.data.users.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jmielczarek on 18/09/2022
 */
interface UsersApi {

    @GET("/users")
    suspend fun loadUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): Response<List<User>>
}