package com.lloydtechassignment.di

import com.lloydtechassignment.data.repository.AnimalRepoImpl
import com.lloydtechassignment.domain.interactor.AnimalUseCase
import com.lloydtechassignment.domain.repository.AnimalRepo
import com.lloydtechassignment.viewmodel.AnimalDetailViewmodel
import com.lloydtechassignment.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Viewmodel Module for declaring all the viewmodels of application
 * The get() function is used into constructor, to inject constructor values.
 * */
val viewModelModule = module {
    viewModel { AnimalViewModel(get()) }
    viewModel { AnimalDetailViewmodel() }
}

/**
 * Repository Module for repository dependency
 * We declared component as single, as singleton instances.*/
val repositoryModule = module {
    single { AnimalRepoImpl(get()) as AnimalRepo }
}

/**
 * Repository Module for repository dependency
 * We declared component as single, as singleton instances.*/
val useCaseModule = module {
    single { AnimalUseCase(get()) }
}


