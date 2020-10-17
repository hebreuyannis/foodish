package com.hebreuyannis.foodishapp.app.di

import android.content.Context
import com.hebreuyannis.foodishapp.app.MainActivity
import com.hebreuyannis.foodishapp.app.https.di.HttpsRequesterModule
import com.hebreuyannis.foodishapp.app.network.di.NetworkModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * here we define all application module for our graph application
 */
@Singleton
@Component(modules = [NetworkModule::class, HttpsRequesterModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {

        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent

    }
    fun inject(activity: MainActivity)

}