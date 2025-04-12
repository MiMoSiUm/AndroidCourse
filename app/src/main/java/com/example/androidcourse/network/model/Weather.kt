package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Weather (

  @SerialName("location" ) var location : Location? = Location(),
  @SerialName("current"  ) var current  : Current?  = Current(),
  @SerialName("forecast" ) var forecast : Forecast? = Forecast()

)