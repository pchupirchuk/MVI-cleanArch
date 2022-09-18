package com.simple.mvi

import android.app.Application
import com.simple.mvi.di.module.activityModule
import com.simple.mvi.di.module.applicationModule
import com.simple.mvi.di.module.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * Created by Rim Gazzah on 8/27/20.
 **/
class MVIApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MVIApplication)
            modules(applicationModule, networkModule, activityModule)
        }
    }
}