package com.lloydtechassignment.domain.fakes


import com.lloydtechassignment.data.models.AnimalUIModel
import com.lloydtechassignment.util.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

object FakeData {
    fun getAnimalList(): Flow<DataState<List<AnimalUIModel>>> = flow {
        emit(DataState.Success(animals))
    }

    val animals = listOf(
        AnimalUIModel(
            id = 1,
            name = "Panda",
            habitat = "China",
            imageLink = "https://dummy.url",
            isFav = 1
        ),
        AnimalUIModel(
            id = 2,
            name = "Tiger",
            habitat = "India",
            imageLink = "https://dummy.url",
            isFav = 0
        ),
        AnimalUIModel(
            id = 2,
            name = "Kangaroo",
            habitat = "Australia",
            imageLink = "https://dummy.url",
            isFav = 1
        )

    )


    fun getFakeAnimal() =
        AnimalUIModel(
                id = 2,
                name = "Elephant",
                habitat = "India",
                imageLink = "https://dummy.url",
                isFav = 1
            )
    }

