package com.example.projet_tdm.retrofit
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import java.util.*

interface authentification {
    @POST("register")
    @FormUrlEncoded
    suspend fun registerUser(@Field("email")  email:String,
                     @Field("nom")  nom:String ,
                     @Field("prenom")  prenom:String ,
                     @Field("photo")  photo:String ,
                     @Field("password")  password:String):Observable<String>

    fun loginUser(@Field("email")  email:String,
                  @Field("password")  password:String ):Observable<String>


}