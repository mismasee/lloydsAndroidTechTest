package com.lloydtechassignment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.interactor.MarkFavUseCase
import kotlinx.coroutines.launch

/**
 * Viewmodel for Animal Detail Activity
 * [markFavUseCase] is used for invoking repository methods which is injected by DI
 * */
class AnimalDetailViewmodel(private val markFavUseCase: MarkFavUseCase) : ViewModel() {

    var animalData: AnimalUIModel? = null

    fun markFavorite() {
        viewModelScope.launch {
            animalData?.let { markFavUseCase.markFavorite(it) }
        }
    }
}