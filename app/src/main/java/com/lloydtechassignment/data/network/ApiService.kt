package com.lloydtechassignment.data.network

import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.util.END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET(END_POINT)
    suspend fun getAnimals(): Response<List<AnimalsRespItem>>
}