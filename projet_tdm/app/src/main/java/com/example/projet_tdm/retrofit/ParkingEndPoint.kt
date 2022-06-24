package com.example.projet_tdm.retrofit

import com.example.projet_tdm.entity.Parking
import com.example.projet_tdm.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ParkingEndPoint {
    @GET("parkings/{parkingId}")
    suspend fun getParkingByiD(@Path("parkingId") parkingId: String): Response<Parking>

    companion object {
        var endpoint: ParkingEndPoint? = null
        fun createEndpoint(): ParkingEndPoint {
            if (endpoint == null) {
                endpoint = Retrofit.Builder().baseUrl(url).addConverterFactory(
                    GsonConverterFactory.create()
                ).build().create(
                    ParkingEndPoint::class.java
                )
            }
            return endpoint!!
        }
    }
}