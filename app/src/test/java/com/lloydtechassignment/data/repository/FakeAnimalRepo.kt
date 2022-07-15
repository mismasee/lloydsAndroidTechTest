package com.lloydtechassignment.data.repository

import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.data.network.ApiService
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class FakeAnimalRepo(private val apiService: ApiService) {

    /* Remote */
    suspend fun getAnimals(): Deferred<DataState<List<AnimalsRespItem>>> {
        return CoroutineScope(Dispatchers.IO).async {
            val response: DataState<List<AnimalsRespItem>> = try {
                DataState.Success(apiService.getAnimals().body())
            } catch (ex: Exception) {
                DataState.Failure(ex)
            }
            response
        }
    }

}