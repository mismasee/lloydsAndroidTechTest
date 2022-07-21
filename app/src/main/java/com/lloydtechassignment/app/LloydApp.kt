package com.lloydtechassignment.app

import android.app.Application
import com.lloydtechassignment.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**Lloyd Application class
 * for initializing Koin and other modules
 * */
class LloydApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //Initialize Koin
        initializeKoin()
    }

    private fun initializeKoin() {
        startKoin {
            androidContext(this@LloydApp)
            modules(
                listOf(
                    repositoryModule,
                    viewModelModule,
                    retrofitModule,
                    useCaseModule,
                    databaseModule,
                    apiModule
                )
            )
        }
    }
}