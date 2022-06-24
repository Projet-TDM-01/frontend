package com.example.projet_tdm.retrofit

import com.example.projet_tdm.url
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface authentification {
    @POST("register")
    @FormUrlEncoded
    suspend fun registerUser(@Field("email")  email:String,
                     @Field("nom")  nom:String ,
                     @Field("prenom")  prenom:String ,
                     @Field("photo")  photo:String ,
                     @Field("password")  password:String)

    fun loginUser(@Field("email")  email:String,
                  @Field("password")  password:String )


}