package com.lloydtechassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lloydtechassignment.CoroutinesTestRule
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.interactor.AnimalUseCase
import com.lloydtechassignment.util.DataState
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AnimalViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = CoroutinesTestRule()
    private lateinit var viewModel: AnimalViewModel
    @Mock
    private lateinit var animalRepository: AnimalUseCase
    @Mock
    private lateinit var animalResponseObserver: Observer<DataState<List<AnimalsRespItem>>>

    @Before
    fun setUp() {
        viewModel = AnimalViewModel(animalRepository)
    }

    @Test
    fun `when fetching results ok then return a list successfully`() {
        val emptyList = arrayListOf<AnimalsRespItem>()
        testCoroutineRule.runBlockingTest {
            viewModel.dataState.observeForever(animalResponseObserver)
            whenever(animalRepository.invoke()).thenAnswer {
                DataState.Success(emptyList)
            }
            viewModel.getAllAnimalFacts()
            assertNotNull(viewModel.dataState.value)
            assertEquals(DataState.Success(emptyList).data!!.isEmpty(),(viewModel.dataState.value as DataState.Success).data!!.isEmpty())
        }
    }

    @Test
    fun `when calling for results then return loading`() {
        testCoroutineRule.runBlockingTest {
            viewModel.dataState.observeForever(animalResponseObserver)
            viewModel.getAllAnimalFacts()
            verify(animalResponseObserver).onChanged(DataState.Loading)
        }
    }

    @Test
    fun `when fetching results fails then return an error`() {
        val exception = RuntimeException("Something went wrong")
        testCoroutineRule.runBlockingTest {
            viewModel.dataState.observeForever(animalResponseObserver)
            whenever(animalRepository.invoke()).thenAnswer {
                DataState.Failure(exception)
            }
            viewModel.getAllAnimalFacts()
            assertNotNull(viewModel.dataState.value)
            assertEquals(DataState.Failure(exception).error,(viewModel.dataState.value as DataState.Failure).error)
        }
    }

    @After
    fun tearDown() {
        viewModel.dataState.removeObserver(animalResponseObserver)
    }
}