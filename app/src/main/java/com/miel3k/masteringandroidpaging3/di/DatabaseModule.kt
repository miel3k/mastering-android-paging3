package com.miel3k.masteringandroidpaging3.di

import android.content.Context
import androidx.room.Room
import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.local.realm.UserRealmLocal
import com.miel3k.masteringandroidpaging3.data.user.local.room.UserRoomLocal
import com.miel3k.masteringandroidpaging3.data.user.remote.UserApi
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemote
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemoteDataSource
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
    annotation class RoomUserLocalDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUserLocalDataSource

    @Singleton
    @RoomUserLocalDataSource
    @Provides
    fun provideRoomUserLocalDataSource(database: MasteringPagingRoomDatabase): UserLocalDataSource {
        return UserRoomLocal(database.userDao())
    }

    @Singleton
    @RealmUserLocalDataSource
    @Provides
    fun provideRealmUserLocalDataSource(realm: Realm): UserLocalDataSource {
        return UserRealmLocal(realm)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(retrofit: Retrofit): UserRemoteDataSource {
        return UserRemote(retrofit.create(UserApi::class.java))
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