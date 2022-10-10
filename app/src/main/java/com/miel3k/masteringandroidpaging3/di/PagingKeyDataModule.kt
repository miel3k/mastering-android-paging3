package com.miel3k.masteringandroidpaging3.di

import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import com.miel3k.masteringandroidpaging3.data.pagingkey.PagingKeyDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.PagingKeyRepository
import com.miel3k.masteringandroidpaging3.data.pagingkey.local.PagingKeyLocalDataSource
import com.miel3k.masteringandroidpaging3.data.pagingkey.local.realm.PagingKeyRealmLocal
import com.miel3k.masteringandroidpaging3.data.pagingkey.local.room.PagingKeyRoomLocal
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import javax.inject.Provider
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Created by miel3k on 20/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object PagingKeyDataModule {

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomPagingKeyLocalDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmPagingKeyLocalDataSource

    @Singleton
    @RoomPagingKeyLocalDataSource
    @Provides
    fun provideRoomPagingKeyLocalDataSource(database: MasteringPagingRoomDatabase): PagingKeyLocalDataSource {
        return PagingKeyRoomLocal(database)
    }

    @Singleton
    @RealmPagingKeyLocalDataSource
    @Provides
    fun provideRealmPagingKeyLocalDataSource(realm: Provider<Realm>): PagingKeyLocalDataSource {
        return PagingKeyRealmLocal(realm)
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RoomPagingKeyDataSource

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class RealmPagingKeyDataSource

    @Singleton
    @RoomPagingKeyDataSource
    @Provides
    fun provideRoomPagingKeyDataSource(@RoomPagingKeyLocalDataSource local: PagingKeyLocalDataSource): PagingKeyDataSource {
        return PagingKeyRepository(local)
    }

    @Singleton
    @RealmPagingKeyDataSource
    @Provides
    fun provideRealmPagingKeyDataSource(@RealmPagingKeyLocalDataSource local: PagingKeyLocalDataSource): PagingKeyDataSource {
        return PagingKeyRepository(local)
    }
}