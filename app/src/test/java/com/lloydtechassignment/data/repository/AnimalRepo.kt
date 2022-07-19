package com.lloydtechassignment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydtechassignment.base.BaseUTTest
import com.lloydtechassignment.data.network.ApiService
import com.lloydtechassignment.di.configureTestAppComponent
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import java.net.HttpURLConnection

@RunWith(JUnit4::class)
class LoginRepositoryTest : BaseUTTest(){

    //Target
    private lateinit var mRepo: AnimalRepoImpl
    //Inject api service created with koin
    val mAPIService : ApiService by inject()

    //Inject Mockwebserver created with koin
    val mockWebServer : MockWebServer by inject()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun test_animals_repo_retrieves_expected_data() =  runBlocking<Unit>{

        mockNetworkResponseWithFileContent("animals.json", HttpURLConnection.HTTP_OK)
        mRepo = AnimalRepoImpl(mAPIService)

        val dataReceived = mRepo.getAnimalFacts()

        Assert.assertNotNull(dataReceived)
    }
}
