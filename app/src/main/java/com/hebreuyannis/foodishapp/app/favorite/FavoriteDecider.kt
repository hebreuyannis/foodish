package com.hebreuyannis.foodishapp.app.favorite

import com.hebreuyannis.foodishapp.app.utils.DispatcherProvider
import kotlinx.coroutines.withContext


interface ShouldRegisterDecider {
    suspend fun shouldRegister(target: String): Boolean
}

class FavoriteDecider(
    private val repository: FavoriteFoodishRepository,
    private val dispatcherProvider: DispatcherProvider
) : ShouldRegisterDecider {

    override suspend fun shouldRegister(target: String): Boolean {
        return withContext(dispatcherProvider.io()) {
            isAlreadyRegister(target)
        }
    }

    private fun isAlreadyRegister(target: String): Boolean {
        val result = repository.getList().find { it == target }
        return result == null
    }
}