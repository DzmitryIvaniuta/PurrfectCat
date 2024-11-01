package com.example.purrfectcat.core.data.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.purrfectcat.core.data.storage.models.CatEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCat(catEntity: CatEntity)

    @Query("SELECT * FROM cats")
    fun getAllCats(): Flow<List<CatEntity>>

    @Query("DELETE FROM cats WHERE id = :catId")
    suspend fun deleteCat(catId: String)
}