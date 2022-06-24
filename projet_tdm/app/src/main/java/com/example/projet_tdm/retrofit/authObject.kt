package com.example.projet_tdm.retrofit

import com.example.projet_tdm.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object authObject {

        var endpoint: Retrofit? = null
        fun getInstance(): Retrofit {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(url).addConverterFactory(
                    GsonConverterFactory.create()
                ).build().create(
                    Retrofit::class.java
                )
            }
            return endpoint!!
        }

}