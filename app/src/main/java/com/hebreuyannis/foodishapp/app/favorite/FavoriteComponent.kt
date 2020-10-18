package com.hebreuyannis.foodishapp.app.favorite

import com.hebreuyannis.foodishapp.app.foodish.FoodishActivity
import dagger.Subcomponent

@Subcomponent(modules = [])
interface FavoriteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): FavoriteComponent
    }

    fun inject(activity: FavoriteActivity)
    fun inject(activity: FoodishActivity)
}