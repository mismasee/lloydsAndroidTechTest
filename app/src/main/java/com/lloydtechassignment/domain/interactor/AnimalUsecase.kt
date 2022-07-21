package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.repository.AnimalRepo
import com.lloydtechassignment.util.DataState


/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
typealias AnimalBaseUseCase = BaseUseCase<Unit,DataState<List<AnimalsRespItem>>>

class AnimalUseCase(private val animalRepo: AnimalRepo
) : AnimalBaseUseCase {

    override suspend operator fun invoke(parameter: Unit) = animalRepo.getAnimalFacts()
}
