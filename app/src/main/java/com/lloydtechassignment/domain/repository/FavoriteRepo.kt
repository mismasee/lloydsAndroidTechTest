package com.lloydtechassignment.domain.repository

import com.lloydtechassignment.presentation.models.AnimalUIModel
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow

interface FavoriteRepo {
    suspend fun addFavorite(animalUIModel: AnimalUIModel)
    suspend fun getAllFavorites(): Flow<DataState<List<AnimalUIModel>>>
}