package com.example.projet_tdm.entity

data class User(
   // @SerializedName("userId") val userId:Int,
 /*   @JsonProperty("userId") */ val userId:Int,
  /*  @JsonProperty("nom") */val nom: String?,

 /*   @JsonProperty("prenom") */val prenom: String?,
   /* @JsonProperty("email")*/ val email: String?,
    /*@JsonProperty(" phone") */val phone: String?,
   /* @JsonProperty(" password") */val password: String?,
)
