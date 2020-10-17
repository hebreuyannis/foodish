package com.hebreuyannis.foodishapp.app.https.model

import java.io.Serializable

enum class ImageEndpoint(val value:String) {
    BIRYANI("biryani"),
    BURGER("burger"),
    DOSA("dosa"),
    IDLY("idly"),
    PIZZA("pizza")
}

data class FoodishImgage(val image:String): Serializable