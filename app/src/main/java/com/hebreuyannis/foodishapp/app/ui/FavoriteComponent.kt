package com.hebreuyannis.foodishapp.app.ui

import dagger.Subcomponent

@FavoriteScope
@Subcomponent
interface FavoriteComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create():FavoriteComponent
    }

    fun inject(activity: FavoriteActivity)
    fun inject(activity: FoodishActivity)
}