package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Condition (

  @SerialName("text" ) var text : String? = null,
  @SerialName("icon" ) var icon : String? = null,
  @SerialName("code" ) var code : Int?    = null

)