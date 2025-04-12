package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Forecastday (

  @SerialName("date"       ) var date      : String?         = null,
  @SerialName("date_epoch" ) var dateEpoch : Int?            = null,
  @SerialName("day"        ) var day       : Day?            = Day(),
  @SerialName("astro"      ) var astro     : Astro?          = Astro(),
  @SerialName("hour"       ) var hour      : ArrayList<Hour> = arrayListOf()

)