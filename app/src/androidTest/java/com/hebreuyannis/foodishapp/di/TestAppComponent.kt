package com.hebreuyannis.foodishapp.di

import com.hebreuyannis.foodishapp.app.di.AppComponent
import com.hebreuyannis.foodishapp.app.di.SubComponent
import com.hebreuyannis.foodishapp.app.https.di.HttpsRequesterModule
import com.hebreuyannis.foodishapp.app.network.di.NetworkModule
import com.hebreuyannis.foodishapp.app.utils.CoroutinesModule
import dagger.Component
import javax.inject.Singleton

/**
 * to generate [DaggerTestAppComponent] you must to launch ./gradlew assembleAndroidTest
 * */
// Replacement for AppComponent in android tests
@Singleton
@Component(modules = [NetworkModule::class, HttpsRequesterModule::class, CoroutinesModule::class, SubComponent::class, FavoriteMockModule::class])
interface TestAppComponent : AppComponent