package com.simple.mvi

import android.content.Context
import com.simple.mvi.di.module.activityModule
import com.simple.mvi.di.module.applicationModule
import com.simple.mvi.di.module.networkModule
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito.mock

class CheckModulesTest : KoinTest {

    // Declare Mock with Mockito
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz -> mock(clazz.java) }

    // verify the Koin configuration
    @Test
    fun checkAllModules() = checkModules {
        androidContext(mock(Context::class.java))
        modules(applicationModule, activityModule, networkModule)
    }
}