package com.lloydtechassignment.data.repository

import com.lloydtechassignment.data.mapper.AnimalEntityMapper
import com.lloydtechassignment.data.source.local.AnimalDao
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.repository.FavoriteRepo
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRepoImpl(private val animalDao: AnimalDao) : FavoriteRepo {

    override suspend fun addFavorite(animalsRespItem: AnimalsRespItem) {
        animalDao.insert(
                AnimalEntityMapper()
                    .mapToEntity(animalsRespItem)
            )
    }


    override suspend fun getAllFavorites()
            : Flow<DataState<List<AnimalsRespItem>>> = flow {
        emit(DataState.Loading)
        try {
            animalDao.getAllFavorites().collect {
                // update UI
                emit(
                    DataState.Success(
                        AnimalEntityMapper().mapFromEntityList(it)
                    )
                )
            }
        } catch (e: Exception) {
            emit(DataState.Failure(e))
        }
    }
}


