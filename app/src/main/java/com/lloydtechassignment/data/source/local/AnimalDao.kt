package com.lloydtechassignment.data.source.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(animalEntity: AnimalEntity): Long

    @Delete
    fun delete(animalEntity: AnimalEntity)

    @Update
    fun update(animalEntity: AnimalEntity)

    @Query("SELECT * FROM animal")
    fun getAllFavorites(): Flow<List<AnimalEntity>>
}