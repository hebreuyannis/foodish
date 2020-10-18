package com.hebreuyannis.foodishapp.app.favorite

import com.hebreuyannis.foodishapp.app.utils.DispatcherProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FavoriteModule {

    @Singleton
    @Provides
    fun providesFavoriteRepository(favoriteComponent: FavoriteComponent.Factory): FavoriteFoodishRepository {
        return FavoriteFoodishDataRepository(
            favoriteComponent
        )
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