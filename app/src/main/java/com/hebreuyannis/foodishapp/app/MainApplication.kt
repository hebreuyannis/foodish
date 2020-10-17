package com.hebreuyannis.foodishapp.app

import android.app.Application
import com.hebreuyannis.foodishapp.app.di.AppComponent
import com.hebreuyannis.foodishapp.app.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class MainApplication: Application() {

    val appComponent:AppComponent by lazy {
        initializeAppComonent()
    }

   open fun initializeAppComonent(): AppComponent {
      return DaggerAppComponent.factory().create(applicationContext)
   }
}