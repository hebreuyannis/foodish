package com.hebreuyannis.foodishapp.app.https.api

import com.hebreuyannis.foodishapp.app.https.model.FoodishImgage
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpsService {

    @GET("https://foodish-api.herokuapp.com/api/images/{images}")
    fun getFoodishImages(@Path("images") images: String): Call<FoodishImgage>
}