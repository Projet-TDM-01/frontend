package com.example.projet_tdm.entity

data class Parking(
    val _id: String,
    val imglink: String,
    val nom: String,
    val commune: String,
    val latitude: Double,
    val longitude: Double,
    val horraireOuver: Int,
    val horraireFerm: Int,
    val tarifHeure: Double,
    val nbPlace: Int,
    val reserved:Int?
)