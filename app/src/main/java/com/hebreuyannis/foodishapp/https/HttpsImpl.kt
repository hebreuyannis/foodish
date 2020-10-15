package com.hebreuyannis.foodishapp.https

import androidx.annotation.WorkerThread
import com.hebreuyannis.foodishapp.https.api.HttpsService
import com.hebreuyannis.foodishapp.https.model.ImageEndpoint
import timber.log.Timber
import java.io.IOException

interface HttpsRequester {
    fun retrieveImages(endpoint: ImageEndpoint)
}

class HttpsImpl(private val httpsService: HttpsService) : HttpsRequester {

    @WorkerThread
    override fun retrieveImages(endpoint: ImageEndpoint) {
        val call = httpsService.getFoodishImages(endpoint.value)
        val response = call.execute()
        if (!response.isSuccessful) {
            throw IOException("Status: ${response.code()} - ${response.errorBody()?.string()}")
        }
        Timber.d("request body ${response.body()}")
    }
}