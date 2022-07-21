package com.lloydtechassignment.data.source.remote

import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.util.END_POINT
import retrofit2.Response
import retrofit2.http.GET

/**
 * API Service class for API interactions
 * */
interface ApiService {

    @GET(END_POINT)
    suspend fun getAnimals(): Response<List<AnimalsRespItem>>
}