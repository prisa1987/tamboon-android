package com.omise.tamboon

import android.app.Application
import com.omise.tamboon.presentation.AppComponent
import com.omise.tamboon.presentation.DaggerAppComponent
import com.omise.tamboon.presentation.charities.di.CharityModule

class TamboonApplication : Application() {

    companion object {
        lateinit var component: AppComponent
    }

    private val BASE_URL = "http://jsonplaceholder.typicode.com"

    override fun onCreate() {
        super.onCreate()
        buildDependencyGraph()
    }

    private fun buildDependencyGraph() {
        component = DaggerAppComponent.builder()
                .charityModule(CharityModule())
                .build()
    }

}