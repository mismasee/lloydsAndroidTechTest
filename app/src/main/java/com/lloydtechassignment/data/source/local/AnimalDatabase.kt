package com.lloydtechassignment.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AnimalEntity::class], version = 1)
abstract class AnimalDatabase : RoomDatabase() {

    abstract fun animalDao(): AnimalDao

    companion object {
        const val DATABASE_NAME: String = "animal_db"
    }
}