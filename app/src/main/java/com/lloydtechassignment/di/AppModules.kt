package com.lloydtechassignment.di

import com.lloydtechassignment.data.repository.AnimalRepo
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
    single { AnimalRepo(get()) }
}


