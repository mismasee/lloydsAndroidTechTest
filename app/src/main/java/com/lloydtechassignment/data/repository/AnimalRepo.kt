package com.lloydtechassignment.data.repository


import com.lloydtechassignment.data.network.ApiService
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimalRepo(private val apiService: ApiService) {

    suspend fun getCatFacts(): DataState<List<AnimalsRespItem>> {
        return withContext(Dispatchers.IO) {
            try {
                //Call this if you have api
                DataState.Success(apiService.getCatContent().body())
            } catch (exception: Exception) {
                //Loading Data from Local Json
                DataState.Failure(exception)
            }
        }
    }


}
