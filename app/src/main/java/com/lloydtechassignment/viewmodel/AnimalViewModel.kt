package com.lloydtechassignment.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.data.repository.AnimalRepo
import com.lloydtechassignment.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(private val animalRepo: AnimalRepo) : ViewModel() {

    private val _dataState: MutableLiveData<DataState<List<AnimalsRespItem>>> = MutableLiveData()

    val dataState: LiveData<DataState<List<AnimalsRespItem>>>
        get() = _dataState

    fun getAllAnimalFacts() {
        viewModelScope.launch {
            _dataState.postValue(animalRepo.getCatFacts())
        }
    }
}