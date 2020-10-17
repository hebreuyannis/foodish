package com.hebreuyannis.foodishapp.app.di

import com.hebreuyannis.foodishapp.app.utils.DefaultDispatcherProvider
import com.hebreuyannis.foodishapp.app.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CoroutinesModule {

    @Provides
    @Singleton
    fun providesDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}