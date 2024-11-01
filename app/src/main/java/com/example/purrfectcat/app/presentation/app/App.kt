package com.example.purrfectcat.app.presentation.app

import android.app.Application
import com.example.purrfectcat.app.presentation.di.AppComponent
import com.example.purrfectcat.app.presentation.di.AppModule
import com.example.purrfectcat.app.presentation.di.DaggerAppComponent

class App : Application(){

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }
}