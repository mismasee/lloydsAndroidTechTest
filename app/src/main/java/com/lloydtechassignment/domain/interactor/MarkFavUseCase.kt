package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.repository.FavoriteRepo

/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */

class MarkFavUseCase(private val favoriteRepo: FavoriteRepo)  {

    suspend fun markFavorite(animalUIModel: AnimalUIModel) {
        favoriteRepo.addFavorite(animalUIModel)
    }
}
