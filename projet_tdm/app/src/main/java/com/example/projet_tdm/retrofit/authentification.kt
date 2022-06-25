package com.example.projet_tdm.retrofit
import com.example.projet_tdm.entity.User
import com.example.projet_tdm.url
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST


interface authentification {
    @POST("login/email")
    @FormUrlEncoded
    /*fun loginUserEmail(@Field("email")  email:String,
                  @Field("password")  password:String ):Observable<String>
*/
    suspend fun loginUserEmail(@FieldMap data:Map<String,String>): Response<List<User>>
    @POST("login/phone")
    @FormUrlEncoded
    suspend fun loginUserPhone(@FieldMap data:Map<String,String>): Response<List<User>>


    @POST("register")
    @FormUrlEncoded
    /*suspend fun registerUser(@Field("email")  email:String,
                     @Field("nom")  nom:String ,
                     @Field("prenom")  prenom:String ,
                     @Field("photo")  photo:String ,
                     @Field("password")  password:String): Response<Void>
*/
    suspend fun registerUser(@FieldMap data:Map<String,String>): Response<Void>
    companion object{
        @Volatile
        var userEndPoint:authentification?=null
        fun createInstance():authentification{
            if(userEndPoint==null){
                synchronized(this){
                    userEndPoint=
                        Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build().create(authentification::class.java)
                }
            }
            return userEndPoint!!
        }
    }

}