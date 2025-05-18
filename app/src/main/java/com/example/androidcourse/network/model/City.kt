package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class City (

  @SerialName("id"      ) var id      : Int?    = null,
  @SerialName("name"    ) var name    : String? = null,
  @SerialName("region"  ) var region  : String? = null,
  @SerialName("country" ) var country : String? = null,
  @SerialName("lat"     ) var lat     : Double? = null,
  @SerialName("lon"     ) var lon     : Double? = null,
  @SerialName("url"     ) var url     : String? = null

)