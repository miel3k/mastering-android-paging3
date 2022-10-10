package com.miel3k.masteringandroidpaging3.di

import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import com.miel3k.masteringandroidpaging3.data.user.UserDataSource
import com.miel3k.masteringandroidpaging3.data.user.UserRepository
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.local.realm.UserRealmLocal
import com.miel3k.masteringandroidpaging3.data.user.local.room.UserRoomLocal
import com.miel3k.masteringandroidpaging3.data.user.remote.UserApi
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemote
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by miel3k on 18/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object UserDataModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomUserDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUserDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomUserLocalDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUserLocalDataSource

    @Singleton
    @RoomUserDataSource
    @Provides
    fun provideRoomUserDataSource(
        @RoomUserLocalDataSource local: UserLocalDataSource,
        remote: UserRemoteDataSource
    ): UserDataSource {
        return UserRepository(local, remote)
    }

    @Singleton
    @RealmUserDataSource
    @Provides
    fun provideRealmUserDataSource(
        @RealmUserLocalDataSource local: UserLocalDataSource,
        remote: UserRemoteDataSource
    ): UserDataSource {
        return UserRepository(local, remote)
    }

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
}