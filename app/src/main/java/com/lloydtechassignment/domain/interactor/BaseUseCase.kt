package com.lloydtechassignment.domain.interactor

interface BaseUseCase<in Parameter,out Result> {
    suspend operator fun invoke(parameter: Parameter):Result
}
