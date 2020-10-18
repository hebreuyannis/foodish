package com.hebreuyannis.foodishapp

import com.hebreuyannis.foodishapp.app.MainApplication
import com.hebreuyannis.foodishapp.app.di.AppComponent
import com.hebreuyannis.foodishapp.di.DaggerTestAppComponent

class MyTestApplication : MainApplication() {

    override fun initializeComponent(): AppComponent {
        // Creates a new TestAppComponent that injects fakes types
        return DaggerTestAppComponent.create()
    }
}