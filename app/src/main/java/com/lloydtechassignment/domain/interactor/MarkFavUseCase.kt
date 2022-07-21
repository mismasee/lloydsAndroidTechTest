package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.repository.FavoriteRepo

/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
typealias MarkFavBaseUseCase = BaseUseCase<AnimalsRespItem, Unit>

class MarkFavUseCase(private val favoriteRepo: FavoriteRepo) : MarkFavBaseUseCase {

    override suspend fun invoke(parameter: AnimalsRespItem) {
        favoriteRepo.addFavorite(parameter)
    }
}
