package com.example.projet_tdm.retrofit

import com.example.projet_tdm.entity.Reservation
import com.example.projet_tdm.url
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ReservationEndPoint {
    // TODO : Change the id   @Path("userId") parkingId: String
    @GET("reservations/62acf391d60753c10ce88e1c")
    suspend fun getUserReservations(): Response<List<Reservation>>

    companion object {
        var endpoint: ReservationEndPoint? = null
        fun createEndpoint(): ReservationEndPoint {
            if (endpoint == null) {
                val gson = GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm")
                    .create()
                endpoint = Retrofit.Builder().baseUrl(url).addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).build().create(
                    ReservationEndPoint::class.java
                )
            }
            return endpoint!!
        }
    }

}