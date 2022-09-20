package com.miel3k.masteringandroidpaging3.data.pagingkey.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.miel3k.masteringandroidpaging3.data.pagingkey.model.PagingKey

/**
 * Created by jmielczarek on 20/09/2022
 */
@Dao
interface PagingKeyDao {
    @Query("SELECT * FROM pagingKey WHERE id = :pagingId")
    fun getPagingKey(pagingId: String): PagingKey?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPagingKey(pagingKey: PagingKey)
}