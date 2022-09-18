package com.miel3k.masteringandroidpaging3.di

import android.content.Context
import androidx.room.Room
import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import com.miel3k.masteringandroidpaging3.data.users.local.UsersLocalDataSource
import com.miel3k.masteringandroidpaging3.data.users.local.realm.UsersRealmLocal
import com.miel3k.masteringandroidpaging3.data.users.local.room.UsersRoomLocal
import com.miel3k.masteringandroidpaging3.data.users.remote.UsersApi
import com.miel3k.masteringandroidpaging3.data.users.remote.UsersRemote
import com.miel3k.masteringandroidpaging3.data.users.remote.UsersRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by jmielczarek on 18/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomUsersLocalDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUsersLocalDataSource

    @Singleton
    @RoomUsersLocalDataSource
    @Provides
    fun provideRoomUsersLocalDataSource(database: MasteringPagingRoomDatabase): UsersLocalDataSource {
        return UsersRoomLocal(database.usersDao())
    }

    @Singleton
    @RealmUsersLocalDataSource
    @Provides
    fun provideRealmUsersLocalDataSource(realm: Realm): UsersLocalDataSource {
        return UsersRealmLocal(realm)
    }

    @Singleton
    @Provides
    fun provideUsersRemoteDataSource(retrofit: Retrofit): UsersRemoteDataSource {
        return UsersRemote(retrofit.create(UsersApi::class.java))
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MasteringPagingRoomDatabase::class.java,
        "mastering_paging_room_database.db"
    ).build()

    @Singleton
    @Provides
    fun provideRealmDatabase() = Realm.getDefaultInstance()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}