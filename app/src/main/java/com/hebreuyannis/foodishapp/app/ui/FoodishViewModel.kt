package com.hebreuyannis.foodishapp.app.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hebreuyannis.foodishapp.app.https.FoodishDownloader
import com.hebreuyannis.foodishapp.app.utils.DispatcherProvider
import com.hebreuyannis.foodishapp.app.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodishViewModel @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    private val foodishDownloader: FoodishDownloader
) : ViewModel() {

    val command: SingleLiveEvent<Command> = SingleLiveEvent()
    var current = ""

    sealed class Command {
        data class LoadFoodish(val url: String) : Command()
        object FavoriteFoodish : Command()
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
                }
            }
        }
    }

    fun favoriteFoodish(url: String) {

        command.value = Command.FavoriteFoodish
    }

    private suspend fun refresh() = withContext(dispatcherProvider.io()) {
        foodishDownloader.downloadFoodish()
    }
}