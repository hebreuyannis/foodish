package com.hebreuyannis.foodishapp.app.favorite

import javax.inject.Singleton

interface FavoriteFoodishRepository {
    fun add(image: String)
    fun delete(image: String)
    fun getList(): List<String>
    fun get(position: Int): String
    var favoriteComponent: FavoriteComponent?
}

@Singleton
class FavoriteFoodishDataRepository(private val favoriteComponentFactory: FavoriteComponent.Factory) :
    FavoriteFoodishRepository {

    override var favoriteComponent: FavoriteComponent? = null

    private var listImages: ArrayList<String> = arrayListOf()

    override fun add(image: String) {
        if (listImages.isEmpty()) {
            initialiseRepository()
        }
        listImages.add(image)
    }

    override fun delete(image: String) {
        listImages.remove(image)
        if (listImages.isEmpty()) {
            desinit()
        }
    }

    override fun getList(): List<String> {
        return listImages
    }

    override fun get(position: Int): String = listImages[position]

    private fun initialiseRepository() {
        favoriteComponent = favoriteComponentFactory.create()
    }

    private fun desinit() {
        favoriteComponent = null
    }

}