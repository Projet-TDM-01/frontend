package com.example.projet_tdm

import com.example.projet_tdm.modals.User
import com.google.gson.GsonBuilder
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


interface RESTAPI {
    @Headers("Content-Type: application/json")
    @POST("")
    suspend fun addUser(@Body userData: User): Response<String>
    @FormUrlEncoded
    @POST("")
    suspend fun getUserId(@FieldMap data:Map<String,String>): Response<String>


    companion object {
        @Volatile
        var endpoint: RESTAPI? = null
        val url = ""
        fun createEndpoint(): RESTAPI {
            if (endpoint == null) {
                synchronized(this) {
                 val gson = GsonBuilder()
                        .setLenient()
                        .create()
                    endpoint = Retrofit.Builder().baseUrl(url)
                        .addConverterFactory(GsonConverterFactory.create(gson)).build()
                        .create(RESTAPI::class.java)
                }
            }
            return endpoint!!

        }
    }
}