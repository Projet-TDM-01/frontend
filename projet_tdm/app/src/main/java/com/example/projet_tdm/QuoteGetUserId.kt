package com.example.projet_tdm

import com.google.gson.annotations.SerializedName

data class QuoteGetUserId(
    @SerializedName("email") val email: String?,
    @SerializedName(" password") val password: String?
)
