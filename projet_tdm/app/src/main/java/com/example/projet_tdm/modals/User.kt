package com.example.projet_tdm.modals

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class User(
   // @SerializedName("userId") val userId:Int,
 /*   @JsonProperty("userId") */ val userId:Int,
  /*  @JsonProperty("nom") */val nom: String?,

 /*   @JsonProperty("prenom") */val prenom: String?,
   /* @JsonProperty("email")*/ val email: String?,
    /*@JsonProperty(" phone") */val phone: String?,
   /* @JsonProperty(" password") */val password: String?,
)
