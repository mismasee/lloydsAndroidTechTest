package com.lloydtechassignment.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lloydtechassignment.base.BaseUTTest
import com.lloydtechassignment.data.source.remote.ApiService
import com.lloydtechassignment.di.configureTestAppComponent
import kotlinx.coroutines.runBlocking
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
    private lateinit var repo: AnimalRepoImpl
    //Inject api service created with koin
    private val apiService : ApiService by inject()


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun start(){
        super.setUp()

        startKoin{ modules(configureTestAppComponent(getMockWebServerUrl()))}
    }

    @Test
    fun `when calling animal repo check if result is expected data`() =  runBlocking {

        mockNetworkResponseWithFileContent("animals.json", HttpURLConnection.HTTP_OK)
        repo = AnimalRepoImpl(apiService)

        val dataReceived = repo.getAnimalFacts()

        Assert.assertNotNull(dataReceived)
    }
}
