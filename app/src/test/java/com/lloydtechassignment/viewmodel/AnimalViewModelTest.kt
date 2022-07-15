package com.lloydtechassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.lloydtechassignment.data.model.AnimalsRespItem
import com.lloydtechassignment.data.repository.AnimalRepo
import com.lloydtechassignment.util.DataState
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AnimalViewModelTest {

    lateinit var SUT: AnimalViewModel

    @Mock
    lateinit var repository: AnimalRepo

    @Mock
    lateinit var dataStateObserver: Observer<DataState<List<AnimalsRespItem>>>

    @Rule
    @JvmField
    var instantTaskExecutorRule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    private var testDispatcher = TestCoroutineDispatcher()
    private var testCoroutineScope = TestCoroutineScope()


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        SUT = AnimalViewModel(repository)
        SUT.dataState.observeForever(dataStateObserver)
    }

    @Test
    fun getAnimals()  {
        val list = List(5) {
            AnimalsRespItem("", "","","","",0,
                "","","","","","",
                "","")
        }
        val dataState = DataState.Success(list)
        testCoroutineScope.launch(testDispatcher){
            whenever(repository.getAnimalFacts()).thenReturn(dataState)
            SUT.getAllAnimalFacts()
            Assert.assertEquals(dataState, SUT.dataState.value)
        }

    }


}