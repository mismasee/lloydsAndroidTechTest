package com.lloydtechassignment.app

import android.app.Application
import com.lloydtechassignment.di.apiModule
import com.lloydtechassignment.di.repositoryModule
import com.lloydtechassignment.di.retrofitModule
import com.lloydtechassignment.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LloydApp : Application(){
    override fun onCreate() {
        super.onCreate()

        //Initialize Koin
        initializeKoin()
    }

    private fun initializeKoin(){
        startKoin {
            androidContext(this@LloydApp)
            modules(listOf(repositoryModule,
                viewModelModule,
                retrofitModule,
                apiModule))
        }
    }
}