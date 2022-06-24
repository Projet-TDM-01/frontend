package com.example.projet_tdm.entity

import java.util.*

data class Reservation(
    val _id: String,
    val dateEntree: Date,
    val dateSortie: Date,
    val numeroPlace: Int,
    val parking: Parking,
)
