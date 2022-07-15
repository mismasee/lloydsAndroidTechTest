package com.lloydtechassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.data.repository.AnimalRepo
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.launch

/**
 * ViewModel for [AnimalActivity]
 */
class AnimalViewModel(private val animalRepo: AnimalRepo) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<AnimalsRespItem>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<AnimalsRespItem>>>
        get() = _dataState

    fun getAllAnimalFacts() {
        _dataState.postValue(DataState.Loading)
        viewModelScope.launch {
            _dataState.postValue(animalRepo.getAnimalFacts())
        }
    }
}