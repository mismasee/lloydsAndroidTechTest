package com.lloydtechassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.domain.interactor.MarkFavUseCase
import com.lloydtechassignment.domain.model.AnimalsRespItem
import kotlinx.coroutines.launch

/**
 * Viewmodel for Animal Detail Activity
 * [markFavUseCase] is used for invoking repository methods which is injected by DI
 * */
class AnimalDetailViewmodel(private val markFavUseCase: MarkFavUseCase) : ViewModel() {

    var animalData: AnimalsRespItem? = null

    fun markFavorite() {
        viewModelScope.launch {
            animalData?.let { markFavUseCase.invoke(it) }
        }
    }
}