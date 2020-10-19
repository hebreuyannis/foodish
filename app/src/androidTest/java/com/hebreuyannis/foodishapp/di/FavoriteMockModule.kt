package com.hebreuyannis.foodishapp.di

import com.hebreuyannis.foodishapp.app.favorite.FavoriteDecider
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository
import com.hebreuyannis.foodishapp.app.favorite.ShouldRegisterDecider
import com.hebreuyannis.foodishapp.app.coroutine.DispatcherProvider
import com.hebreuyannis.foodishapp.model.FavoriteRepositoryMock
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteMockModule {

    @Provides
    @Singleton
    fun provideRepositoryMock(): FavoriteFoodishRepository {
        return FavoriteRepositoryMock()
    }

    @Provides
    fun providesFavoriteDecider(
        favoriteRepository: FavoriteFoodishRepository,
        dispatcherProvider: DispatcherProvider
    ): ShouldRegisterDecider {
        return FavoriteDecider(
            favoriteRepository,
            dispatcherProvider
        )
    }
}