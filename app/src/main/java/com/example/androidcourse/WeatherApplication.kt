package com.example.androidcourse

import android.app.Application
import com.example.androidcourse.data.AppContainer
import com.example.androidcourse.data.DefaultAppContainer

class WeatherApplication : Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}