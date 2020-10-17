package com.hebreuyannis.foodishapp.app.https.di

import com.hebreuyannis.foodishapp.app.https.HttpsImpl
import com.hebreuyannis.foodishapp.app.https.HttpsRequester
import com.hebreuyannis.foodishapp.app.https.api.HttpsService
import dagger.Module
import dagger.Provides

@Module
class HttpsRequesterModule {

    @Provides
    fun httpsRequester(service: HttpsService):HttpsRequester {
        return HttpsImpl(service)
    }

}