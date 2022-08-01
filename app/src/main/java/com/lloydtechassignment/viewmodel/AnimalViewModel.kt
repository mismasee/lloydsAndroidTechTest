package com.lloydtechassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.interactor.AnimalUseCase
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.launch

/**
 * ViewModel for AnimalActivity
 */
class AnimalViewModel(private val animalUseCase: AnimalUseCase) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<AnimalUIModel>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<AnimalUIModel>>>
        get() = _dataState

    fun getAllAnimalFacts() {
        _dataState.postValue(DataState.Loading)
        viewModelScope.launch {
            _dataState.postValue(animalUseCase())
        }
    }
}