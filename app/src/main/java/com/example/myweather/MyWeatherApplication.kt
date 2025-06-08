package com.example.myweather

import android.app.Application
import com.example.myweather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyWeatherApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(Level.DEBUG)
            androidContext(this@MyWeatherApplication)
            modules(appModule)
        }
    }
}