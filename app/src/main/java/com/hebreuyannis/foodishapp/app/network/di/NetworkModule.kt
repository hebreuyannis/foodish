package com.hebreuyannis.foodishapp.app.network

import com.hebreuyannis.foodishapp.app.https.api.HttpsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(): OkHttpClient {
        return OkHttpClient.Builder().
    }


    @Provides
    fun httpsRequesterService(retrofit: Retrofit): HttpsService =
            retrofit.create(HttpsService::class.java)
}