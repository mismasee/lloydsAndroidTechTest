package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.repository.FavoriteRepo
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow

/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class GetFavUseCase(private val favoriteRepo: FavoriteRepo)  {

    suspend operator fun invoke(): Flow<DataState<List<AnimalUIModel>>> =
        favoriteRepo.getAllFavorites()
}