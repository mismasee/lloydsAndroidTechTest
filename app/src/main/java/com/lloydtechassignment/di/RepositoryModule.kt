package com.lloydtechassignment.di

import com.lloydtechassignment.data.network.ApiService
import com.lloydtechassignment.data.repository.AnimalRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCatRepository(apiService: ApiService): AnimalRepo {
        return AnimalRepo(apiService)
    }
}