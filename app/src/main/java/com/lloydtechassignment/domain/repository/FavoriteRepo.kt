package com.lloydtechassignment.domain.repository

import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    suspend fun addFavorite(animalsRespItem: AnimalsRespItem)
    suspend fun getAllFavorites(): Flow<DataState<List<AnimalsRespItem>>>
}