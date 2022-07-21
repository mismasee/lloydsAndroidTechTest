package com.lloydtechassignment.domain.interactor

import com.lloydtechassignment.domain.fakes.FakeData
import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.domain.repository.FavoriteRepo
import com.lloydtechassignment.domain.utils.DomainBaseTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MarkFavoriteUseCaseTest : DomainBaseTest() {

    @Mock
    lateinit var favRepository: FavoriteRepo

    lateinit var sut: MarkFavUseCase

    var favAnimalList = mutableListOf<AnimalsRespItem>()

    @Before
    fun setUp() {
        sut = MarkFavUseCase(favRepository)
    }

    @Test
    fun `check if favorite animal added in to favorite list`() =
        dispatcher.runBlockingTest {
            val favItem = FakeData.getFakeAnimal()
            favRepository.addFavorite(favItem)
            addInFavList(favItem)

            assert(favAnimalList.contains(favItem))
        }


    @Test
    fun `check if favorite animal not added in to favorite list`() =
        dispatcher.runBlockingTest {
            val favItem = FakeData.getFakeAnimal()
            favRepository.addFavorite(favItem)

            assert(!favAnimalList.contains(favItem))
        }

    private fun addInFavList(animalsRespItem: AnimalsRespItem){
        favAnimalList.add(animalsRespItem)
    }
}



