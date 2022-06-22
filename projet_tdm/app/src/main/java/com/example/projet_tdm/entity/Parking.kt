package com.example.projet_tdm.entity

data class Parking(
    val parkingId: String ,
    val picture :String ,
    val openHour : Int ,
    val closeHour : Int ,
    val codeQR : Long ,
    val tarif : Float
)
