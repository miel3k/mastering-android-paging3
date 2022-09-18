package com.miel3k.masteringandroidpaging3.di

import com.miel3k.masteringandroidpaging3.data.users.UsersDataSource
import com.miel3k.masteringandroidpaging3.data.users.UsersRepository
import com.miel3k.masteringandroidpaging3.data.users.local.UsersLocalDataSource
import com.miel3k.masteringandroidpaging3.data.users.remote.UsersRemoteDataSource
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
object UsersRepositoryModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomUsersDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmUsersDataSource

    @Singleton
    @RoomUsersDataSource
    @Provides
    fun provideRoomUsersDataSource(
        @DatabaseModule.RoomUsersLocalDataSource local: UsersLocalDataSource,
        remote: UsersRemoteDataSource
    ): UsersDataSource {
        return UsersRepository(local, remote)
    }

    @Singleton
    @RealmUsersDataSource
    @Provides
    fun provideRealmUsersDataSource(
        @DatabaseModule.RealmUsersLocalDataSource local: UsersLocalDataSource,
        remote: UsersRemoteDataSource
    ): UsersDataSource {
        return UsersRepository(local, remote)
    }
}