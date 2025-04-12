package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Astro (

  @SerialName("sunrise"           ) var sunrise          : String? = null,
  @SerialName("sunset"            ) var sunset           : String? = null,
  @SerialName("moonrise"          ) var moonrise         : String? = null,
  @SerialName("moonset"           ) var moonset          : String? = null,
  @SerialName("moon_phase"        ) var moonPhase        : String? = null,
  @SerialName("moon_illumination" ) var moonIllumination : Int?    = null,
  @SerialName("is_moon_up"        ) var isMoonUp         : Int?    = null,
  @SerialName("is_sun_up"         ) var isSunUp          : Int?    = null

)