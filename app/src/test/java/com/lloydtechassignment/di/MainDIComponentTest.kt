package com.lloydtechassignment.di

/**
 * Main Koin DI component.
 * Helps to configure
 * 1) Mockwebserver
 * 3) Repository
 */
fun configureTestAppComponent(baseApi: String)
        = listOf(
    MockWebServerDIPTest,
    configureNetworkModuleForTest(baseApi),
    viewModelModule,
    repositoryModule
    )

