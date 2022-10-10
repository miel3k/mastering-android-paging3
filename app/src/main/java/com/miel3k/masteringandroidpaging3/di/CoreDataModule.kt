package com.miel3k.masteringandroidpaging3.di

import android.content.Context
import androidx.room.Room
import com.miel3k.masteringandroidpaging3.data.database.MasteringPagingRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by miel3k on 18/09/2022
 */
@Module
@InstallIn(SingletonComponent::class)
object CoreDataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MasteringPagingRoomDatabase::class.java,
        "mastering_paging_room_database.db"
    ).build()

    @Singleton
    @Provides
    fun provideRealmDatabase(): Realm = Realm.getDefaultInstance()

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val client = OkHttpClient.Builder().build()
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}