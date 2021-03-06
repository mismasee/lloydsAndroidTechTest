package com.lloydtechassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.interactor.GetFavUseCase
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class FavoriteViewmodel(private val getFavUseCase: GetFavUseCase): ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<AnimalUIModel>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<AnimalUIModel>>>
        get() = _dataState


    fun getAllFavoriteList(){
        viewModelScope.launch {
            getFavUseCase.getAllFavorites()
                .onEach { dataState ->
                    _dataState.value = dataState
                }.launchIn(viewModelScope)
        }
    }

}