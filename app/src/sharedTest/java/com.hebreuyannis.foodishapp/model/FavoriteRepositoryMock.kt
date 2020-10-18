package com.hebreuyannis.foodishapp.model

import com.hebreuyannis.foodishapp.app.favorite.FavoriteComponent
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository

class FavoriteRepositoryMock : FavoriteFoodishRepository {

    private var listMock = arrayListOf<String>(
        "https://foodish-api.herokuapp.com/images/burger/burger58.jpg",
        "https://foodish-api.herokuapp.com/images/burger/burger56.jpg",
        "https://foodish-api.herokuapp.com/images/burger/burger57.jpg"
    )

    override fun add(image: String) {
        listMock.add(image)
    }

    override fun delete(image: String) {
        listMock.remove(image)
    }

    override fun getList(): List<String> = listMock

    override fun get(position: Int): String {
        return listMock[position]
    }

    override var favoriteComponent: FavoriteComponent? = null
}