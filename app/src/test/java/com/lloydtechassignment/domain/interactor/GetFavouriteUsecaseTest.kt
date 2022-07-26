package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.domain.fakes.FakeData
import com.lloydtechassignment.domain.model.AnimalDomainModel
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
    private lateinit var favRepository: FavoriteRepo

    private lateinit var sut: GetFavUseCase

    private var favAnimalList = mutableListOf<AnimalUIModel>()

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

    private fun addInFavList() : Flow<DataState<List<AnimalUIModel>>> {
        favAnimalList.addAll(FakeData.animals)
        return FakeData.getAnimalList()
    }
}



