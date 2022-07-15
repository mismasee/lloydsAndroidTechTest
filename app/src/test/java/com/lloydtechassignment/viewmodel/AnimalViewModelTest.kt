package com.lloydtechassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lloydtechassignment.CoroutinesTestRule
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.data.repository.AnimalRepo
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AnimalViewModelTest {

    private lateinit var viewModel: AnimalViewModel


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()

    @Mock
    private lateinit var animalRepo: AnimalRepo

    @Mock
    private lateinit var animalDataObserver: Observer<DataState<List<AnimalsRespItem>>>

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun getAnimals_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            doReturn(DataState.Success(emptyList<AnimalsRespItem>()))
                .`when`(animalRepo)
                .getAnimalFacts()

            val viewModel = AnimalViewModel(animalRepo)
            viewModel.dataState.observeForever(animalDataObserver)
            verify(animalRepo).getAnimalFacts()
            verify(animalDataObserver).onChanged(DataState.Success(emptyList()))
            viewModel.dataState.removeObserver(animalDataObserver)
        }
    }

    @Test
    fun getAnimals_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
            doThrow(RuntimeException(errorMessage))
                .`when`(animalRepo)
                .getAnimalFacts()
            val viewModel = AnimalViewModel(animalRepo)
            viewModel.dataState.observeForever(animalDataObserver)
            verify(animalRepo).getAnimalFacts()
            verify(animalDataObserver).onChanged(
                DataState.Failure(RuntimeException(errorMessage))
            )
            viewModel.dataState.removeObserver(animalDataObserver)
        }
    }


    @After
    fun tearDown() {
        // do something if required
    }

}

