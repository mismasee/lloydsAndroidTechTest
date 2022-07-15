package com.lloydtechassignment.di


import com.lloydtechassignment.data.repository.AnimalRepo
import com.lloydtechassignment.viewmodel.AnimalViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimalViewModel(get()) }
}

val repositoryModule = module {
    single { AnimalRepo(get()) }
}


