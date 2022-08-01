package com.lloydtechassignment.di

import android.app.Application
import androidx.room.Room
import com.lloydtechassignment.data.source.local.AnimalDao
import com.lloydtechassignment.data.source.local.AnimalDatabase
import org.koin.dsl.module

val databaseModule = module {

     fun provideAppDatabase(application: Application): AnimalDatabase {
        return Room.databaseBuilder(
            application,
            AnimalDatabase::class.java,
            AnimalDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

    fun provideAnimalDao(appDatabase: AnimalDatabase): AnimalDao {
        return appDatabase.animalDao()
    }

    single { provideAppDatabase(get()) }
    single { provideAnimalDao(get()) }
}
