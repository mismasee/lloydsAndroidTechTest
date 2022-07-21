package com.lloydtechassignment.domain.fakes


import com.lloydtechassignment.domain.model.AnimalsRespItem
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getAnimalList(): Flow<DataState<List<AnimalsRespItem>>> = flow {
        emit(DataState.Success(animals))
    }

    val animals = listOf(
        AnimalsRespItem(
            id = 1,
            name = "Panda",
            habitat = "China",
            image_link = "https://dummy.url",
            isFav = 1
        ),
        AnimalsRespItem(
            id = 2,
            name = "Tiger",
            habitat = "India",
            image_link = "https://dummy.url",
            isFav = 0
        ),
        AnimalsRespItem(
            id = 2,
            name = "Kangaroo",
            habitat = "Australia",
            image_link = "https://dummy.url",
            isFav = 1
        )

    )


    fun getFakeAnimal() =
            AnimalsRespItem(
                id = 2,
                name = "Elephant",
                habitat = "India",
                image_link = "https://dummy.url",
                isFav = 1
            )
    }

