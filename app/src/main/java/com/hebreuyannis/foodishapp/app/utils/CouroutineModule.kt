package com.hebreuyannis.foodishapp.app.utils

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