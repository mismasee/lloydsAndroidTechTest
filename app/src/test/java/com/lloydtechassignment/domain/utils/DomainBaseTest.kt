package com.lloydtechassignment.domain.utils

import com.lloydtechassignment.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineExceptionHandler
import org.junit.Rule

@ExperimentalCoroutinesApi
abstract class DomainBaseTest {
    /**
     * A test rule to allow testing coroutines that use the main dispatcher
     */
    @ExperimentalCoroutinesApi
    @get:Rule
    val testRule = CoroutinesTestRule()

    val dispatcher = testRule.testCoroutineDispatcher

    val exceptionHandler = TestCoroutineExceptionHandler()
}
