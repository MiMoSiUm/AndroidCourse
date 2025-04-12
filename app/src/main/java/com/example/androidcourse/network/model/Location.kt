package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Location (

  @SerialName("name"            ) var name           : String? = null,
  @SerialName("region"          ) var region         : String? = null,
  @SerialName("country"         ) var country        : String? = null,
  @SerialName("lat"             ) var lat            : Double? = null,
  @SerialName("lon"             ) var lon            : Double? = null,
  @SerialName("tz_id"           ) var tzId           : String? = null,
  @SerialName("localtime_epoch" ) var localtimeEpoch : Int?    = null,
  @SerialName("localtime"       ) var localtime      : String? = null

)