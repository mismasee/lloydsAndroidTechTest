package com.lloydtechassignment.domain.repository

import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.util.DataState

interface AnimalRepo {
    suspend fun getAnimalFacts(): DataState<List<AnimalUIModel>>
}