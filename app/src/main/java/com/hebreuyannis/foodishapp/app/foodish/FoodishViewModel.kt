package com.hebreuyannis.foodishapp.app.foodish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.foodishapp.app.favorite.FavoriteFoodishRepository
import com.hebreuyannis.foodishapp.app.favorite.ShouldRegisterDecider
import com.hebreuyannis.foodishapp.app.https.FoodishDownloader
import com.hebreuyannis.foodishapp.app.utils.DispatcherProvider
import com.hebreuyannis.foodishapp.app.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodishViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val foodishDownloader: FoodishDownloader,
    private val repository: FavoriteFoodishRepository,
    private val favoriteDecider: ShouldRegisterDecider
) : ViewModel() {

    val command: SingleLiveEvent<Command> = SingleLiveEvent()
    var current = ""

    sealed class Command {
        data class LoadFoodish(val url: String) : Command()
        object FavoriteFoodish : Command()
        object DeleteFavoriteFoodish : Command()
        object ErrorLoading : Command()
    }

    fun refreshFoodish() {
        viewModelScope.launch {
            val image = refresh()
            withContext(dispatcherProvider.main()) {
                if (image == null) {
                    command.value = Command.ErrorLoading
                } else {
                    current = image.image
                    command.value = Command.LoadFoodish(image.image)
                    if (shouldDisplayStar(image.image)) {
                        command.value = Command.FavoriteFoodish
                    }
                }
            }
        }
    }

    fun favoriteFoodish(url: String) {
        viewModelScope.launch {
            if (favoriteDecider.shouldRegister(url)) {
                repository.add(url)
                withContext(dispatcherProvider.main()) {
                    command.value = Command.FavoriteFoodish
                }
            } else {
                repository.delete(url)
                withContext(dispatcherProvider.main()) {
                    command.value = Command.DeleteFavoriteFoodish
                }
            }
        }
    }

    private suspend fun refresh() = withContext(dispatcherProvider.io()) {
        foodishDownloader.downloadFoodish()
    }

    private suspend fun shouldDisplayStar(target: String): Boolean {
        return !favoriteDecider.shouldRegister(target)
    }
}