package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.repository.AnimalRepo
import com.lloydtechassignment.util.DataState


/**
 * An interactor that calls the actual implementation of [AnimalRepo](which is injected by DI)
 * it handles the response that returns data &
 * contains a list of actions, event steps
 */

class AnimalUseCase(private val animalRepo: AnimalRepo
)  {

  suspend  fun getAnimalFacts() = animalRepo.getAnimalFacts()
}
