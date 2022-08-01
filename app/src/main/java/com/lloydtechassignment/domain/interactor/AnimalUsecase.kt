package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.domain.repository.AnimalRepo


/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */
class AnimalUseCase(private val animalRepo: AnimalRepo)  {

  suspend operator fun invoke() = animalRepo.getAnimalFacts()
}
