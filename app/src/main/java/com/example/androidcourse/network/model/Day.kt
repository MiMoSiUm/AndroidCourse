package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Day (

  @SerialName("maxtemp_c"            ) var maxtempC          : Double?    = null,
  @SerialName("maxtemp_f"            ) var maxtempF          : Double?    = null,
  @SerialName("mintemp_c"            ) var mintempC          : Double?    = null,
  @SerialName("mintemp_f"            ) var mintempF          : Double?    = null,
  @SerialName("avgtemp_c"            ) var avgtempC          : Double?    = null,
  @SerialName("avgtemp_f"            ) var avgtempF          : Double?    = null,
  @SerialName("maxwind_mph"          ) var maxwindMph        : Double?    = null,
  @SerialName("maxwind_kph"          ) var maxwindKph        : Double?    = null,
  @SerialName("totalprecip_mm"       ) var totalprecipMm     : Double?    = null,
  @SerialName("totalprecip_in"       ) var totalprecipIn     : Double?    = null,
  @SerialName("totalsnow_cm"         ) var totalsnowCm       : Double?    = null,
  @SerialName("avgvis_km"            ) var avgvisKm          : Double?    = null,
  @SerialName("avgvis_miles"         ) var avgvisMiles       : Double?    = null,
  @SerialName("avghumidity"          ) var avghumidity       : Int?       = null,
  @SerialName("daily_will_it_rain"   ) var dailyWillItRain   : Int?       = null,
  @SerialName("daily_chance_of_rain" ) var dailyChanceOfRain : Int?       = null,
  @SerialName("daily_will_it_snow"   ) var dailyWillItSnow   : Int?       = null,
  @SerialName("daily_chance_of_snow" ) var dailyChanceOfSnow : Int?       = null,
  @SerialName("condition"            ) var condition         : Condition? = Condition(),
  @SerialName("uv"                   ) var uv                : Double?    = null

)