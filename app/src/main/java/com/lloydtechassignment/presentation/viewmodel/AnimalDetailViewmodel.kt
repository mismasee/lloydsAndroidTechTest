package com.lloydtechassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.presentation.models.AnimalUIModel
import com.lloydtechassignment.domain.usecases.MarkFavUseCase
import kotlinx.coroutines.launch

/**
 * Viewmodel for Animal Detail Activity
 * [markFavUseCase] is used for invoking repository methods which is injected by DI
 * */
class AnimalDetailViewmodel(private val markFavUseCase: MarkFavUseCase) : ViewModel() {

    var animalData: AnimalUIModel? = null

    fun markFavorite() {
        viewModelScope.launch {
            animalData?.let { markFavUseCase(it) }
        }
    }
}