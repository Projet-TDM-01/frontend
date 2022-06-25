package com.example.projet_tdm.retrofit
import android.graphics.Bitmap
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
interface Reservations {

    @POST("createReservation")
    @FormUrlEncoded
    suspend fun addReservation(
                             @Field("parking_id")  parking:Int ,
                             @Field("date")  date:java.util.Date ,
                             @Field("startHour")   startHour: Float,
                             @Field("EndHour")  endHour:Float ,
                             @Field("place")  place:Int ,
                             @Field("place")  codeQR:Bitmap? ,
                             @Field("state")  state:Int? ,

    ):Observable<String>

}