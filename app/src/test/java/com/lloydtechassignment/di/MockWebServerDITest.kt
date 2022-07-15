package com.lloydtechassignment.di

import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module

/**
 * Creates Webserver instance for testing
 */
val MockWebServerDIPTest = module {

    factory {
        MockWebServer()
    }

}