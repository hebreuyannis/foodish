package com.hebreuyannis.foodishapp

import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MainApplication:  Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    open lateinit var daggerAppComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
    }

    protected open fun configureDependencyInjection() {
        daggerAppComponent = Dagg
            .application(this)
            .build()
        daggerAppComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}