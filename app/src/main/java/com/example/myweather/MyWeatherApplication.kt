package com.example.myweather

import android.app.Application
import com.example.myweather.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyWeatherApplication : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@MyWeatherApplication)
            modules(appModule)
        }
    }
}