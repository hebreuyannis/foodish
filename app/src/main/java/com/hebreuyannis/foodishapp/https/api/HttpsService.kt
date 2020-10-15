package com.hebreuyannis.foodishapp.https.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HttpsService {

    @GET("https://foodish-api.herokuapp.com/api/images/{images}")
    fun getFoodishImages(@Path("images") images: String): Call<ResponseBody>
}