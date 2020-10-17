package com.hebreuyannis.foodishapp.app.network.di

import com.hebreuyannis.foodishapp.app.https.api.HttpsService
import com.hebreuyannis.foodishapp.app.network.ApiRequestInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(apiRequestInterceptor: ApiRequestInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(apiRequestInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun apiRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://foodish-api.herokuapp.com")
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun apiRequestInterceptor(): ApiRequestInterceptor {
        return ApiRequestInterceptor()
    }


    @Provides
    fun httpsRequesterService(retrofit: Retrofit): HttpsService =
        retrofit.create(HttpsService::class.java)
}