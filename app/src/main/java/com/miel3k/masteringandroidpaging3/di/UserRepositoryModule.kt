package com.miel3k.masteringandroidpaging3.di

import com.miel3k.masteringandroidpaging3.data.user.UserDataSource
import com.miel3k.masteringandroidpaging3.data.user.UserRepository
import com.miel3k.masteringandroidpaging3.data.user.local.UserLocalDataSource
import com.miel3k.masteringandroidpaging3.data.user.remote.UserRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by jmielczarek on 18/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object UserRepositoryModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomUserDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUserDataSource

    @Singleton
    @RoomUserDataSource
    @Provides
    fun provideRoomUserDataSource(
        @DatabaseModule.RoomUserLocalDataSource local: UserLocalDataSource,
        remote: UserRemoteDataSource
    ): UserDataSource {
        return UserRepository(local, remote)
    }

    @Singleton
    @RealmUserDataSource
    @Provides
    fun provideRealmUserDataSource(
        @DatabaseModule.RealmUserLocalDataSource local: UserLocalDataSource,
        remote: UserRemoteDataSource
    ): UserDataSource {
        return UserRepository(local, remote)
    }
}