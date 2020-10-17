package com.hebreuyannis.foodishapp.app.ui

import java.text.FieldPosition
import javax.inject.Inject

interface FavoriteFoodishRepository {
    fun add(image:String)
    fun delete(image: String)
    fun getListe():List<String>
    fun get(position:Int):String
}

@FavoriteScope
class FavoriteFoodishDataRepository @Inject constructor():FavoriteFoodishRepository {
    override fun add(image: String) {
        TODO("Not yet implemented")
    }

    override fun delete(image: String) {
        TODO("Not yet implemented")
    }

    override fun getListe(): List<String> {
        TODO("Not yet implemented")
    }

    override fun get(position: Int): String {
        TODO("Not yet implemented")
    }

}