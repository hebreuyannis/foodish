package com.hebreuyannis.foodishapp.app.https

import androidx.annotation.WorkerThread
import com.hebreuyannis.foodishapp.app.https.api.HttpsService
import com.hebreuyannis.foodishapp.app.https.model.FoodishImgage
import com.hebreuyannis.foodishapp.app.https.model.ImageEndpoint
import timber.log.Timber
import java.io.IOException

interface HttpsRequester {
    @WorkerThread
    fun retrieveImages(endpoint: ImageEndpoint):FoodishImgage?
}

class HttpsImpl(private val httpsService: HttpsService) : HttpsRequester {

    @WorkerThread
    override fun retrieveImages(endpoint: ImageEndpoint):FoodishImgage? {
        val call = httpsService.getFoodishImages(endpoint.value)
        val response = call.execute()
        if (!response.isSuccessful) {
            throw IOException("Status: ${response.code()} - ${response.errorBody()?.string()}")
        }
        val image = response.body()
        if(image == null){
            Timber.d("No image was load")
        }
        return image
    }
}

//TODO: faire page de selection aleatoire
//TODO: faire page liked photo
//TODO: faire gestion d'erreur exception + snackbar