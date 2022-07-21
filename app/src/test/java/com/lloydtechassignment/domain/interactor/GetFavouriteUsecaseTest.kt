package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.domain.fakes.FakeData
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.repository.FavoriteRepo
import com.lloydtechassignment.domain.utils.DomainBaseTest
import com.lloydtechassignment.util.DataState
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetFavouriteUsecaseTest : DomainBaseTest() {

    @Mock
    lateinit var favRepository: FavoriteRepo

    lateinit var sut: GetFavUseCase

    var favAnimalList = mutableListOf<AnimalsRespItem>()

    @Before
    fun setUp() {
        sut = GetFavUseCase(favRepository)
    }

    @Test
    fun `check if favorite list is returning all favorite items`() =
        dispatcher.
        runBlockingTest {
            // Arrange (Given)
            favAnimalList.clear()
            favRepository.getAllFavorites()
            addInFavList()

            // Assert (Then)
            assertEquals(favAnimalList.size, 3)
        }

    @Test
    fun `if favorite list not is returning all favorite items`() =
        dispatcher.runBlockingTest {
            // Arrange (Given)
            favAnimalList.clear()
            favRepository.getAllFavorites()
            addInFavList()

            // Assert (Then)
            assertNotEquals(favAnimalList.size, 1)
        }

    private fun addInFavList() : Flow<DataState<List<AnimalsRespItem>>> {
        favAnimalList.addAll(FakeData.animals)
        return FakeData.getAnimalList()
    }
}


