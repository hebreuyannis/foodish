package com.hebreuyannis.foodishapp.app.https

import com.hebreuyannis.foodishapp.app.https.model.FoodishImgage
import com.hebreuyannis.foodishapp.app.https.model.ImageEndpoint
import kotlin.random.Random

interface FoodishDownloader {
    fun downloadFoodish(): FoodishImgage?
}

class FoodishDownloaderImpl(private val httpsRequester: HttpsRequester) :
    FoodishDownloader {

    override fun downloadFoodish(): FoodishImgage? = httpsRequester.retrieveImages(randomEndpoint())

    private fun randomEndpoint(): ImageEndpoint =
        ImageEndpoint.values()[Random.nextInt(0, ImageEndpoint.values().size)]

}