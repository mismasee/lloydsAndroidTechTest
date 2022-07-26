package com.lloydtechassignment.data.repository

import com.lloydtechassignment.data.mapper.animalmapper.AnimalUIModelMapper
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.data.source.remote.ApiService
import com.lloydtechassignment.domain.repository.AnimalRepo
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Animal Repository for fetching Animal List
 * parameter [apiService] injected using Koin Dependency Injection
 * */
class AnimalRepoImpl(private val apiService: ApiService) : AnimalRepo {

    override suspend fun getAnimalFacts(): DataState<List<AnimalUIModel>> {
        return withContext(Dispatchers.IO) {
            try {
                DataState.Success(AnimalUIModelMapper().mapFromEntityList(apiService.getAnimals().body()!!))
            } catch (exception: Exception) {
                DataState.Failure(exception)
            }
        }
    }


}
