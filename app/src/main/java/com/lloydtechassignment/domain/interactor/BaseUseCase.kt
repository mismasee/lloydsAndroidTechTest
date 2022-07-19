package com.lloydtechassignment.domain.interactor

interface BaseUseCase<out Result> {
    suspend operator fun invoke():Result
}
