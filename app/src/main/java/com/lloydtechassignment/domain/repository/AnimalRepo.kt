package com.lloydtechassignment.domain.repository

import com.lloydtechassignment.presentation.models.AnimalUIModel
import com.lloydtechassignment.util.DataState

interface AnimalRepo {
    suspend fun getAnimalFacts(): DataState<List<AnimalUIModel>>
}