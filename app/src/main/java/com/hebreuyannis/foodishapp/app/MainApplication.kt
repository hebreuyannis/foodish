package com.hebreuyannis.foodishapp.app

import android.app.Application
import com.hebreuyannis.foodishapp.app.di.AppComponent
import com.hebreuyannis.foodishapp.app.di.DaggerAppComponent

open class MainApplication : Application() {

    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}