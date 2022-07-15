package com.lloydtechassignment.data.repository

import com.lloydtechassignment.data.network.ApiService
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Animal Repository for fetching Animal List
 * parameter [apiService] injected using Koin Dependency Injection
 * */
class AnimalRepo(private val apiService: ApiService) {

    suspend fun getAnimalFacts(): DataState<List<AnimalsRespItem>> {
        return withContext(Dispatchers.IO) {
            try {
                DataState.Success(apiService.getAnimals().body())
            } catch (exception: Exception) {
                DataState.Failure(exception)
            }
        }
    }


}
