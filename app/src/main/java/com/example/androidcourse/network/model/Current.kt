package com.example.androidcourse.network.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class Current (

  @SerialName("last_updated_epoch" ) var lastUpdatedEpoch : Int?       = null,
  @SerialName("last_updated"       ) var lastUpdated      : String?    = null,
  @SerialName("temp_c"             ) var tempC            : Double?    = null,
  @SerialName("temp_f"             ) var tempF            : Double?    = null,
  @SerialName("is_day"             ) var isDay            : Int?       = null,
  @SerialName("condition"          ) var condition        : Condition? = Condition(),
  @SerialName("wind_mph"           ) var windMph          : Double?    = null,
  @SerialName("wind_kph"           ) var windKph          : Double?    = null,
  @SerialName("wind_degree"        ) var windDegree       : Int?       = null,
  @SerialName("wind_dir"           ) var windDir          : String?    = null,
  @SerialName("pressure_mb"        ) var pressureMb       : Double?    = null,
  @SerialName("pressure_in"        ) var pressureIn       : Double?    = null,
  @SerialName("precip_mm"          ) var precipMm         : Double?    = null,
  @SerialName("precip_in"          ) var precipIn         : Double?    = null,
  @SerialName("humidity"           ) var humidity         : Int?       = null,
  @SerialName("cloud"              ) var cloud            : Int?       = null,
  @SerialName("feelslike_c"        ) var feelslikeC       : Double?    = null,
  @SerialName("feelslike_f"        ) var feelslikeF       : Double?    = null,
  @SerialName("windchill_c"        ) var windchillC       : Double?    = null,
  @SerialName("windchill_f"        ) var windchillF       : Double?    = null,
  @SerialName("heatindex_c"        ) var heatindexC       : Double?    = null,
  @SerialName("heatindex_f"        ) var heatindexF       : Double?    = null,
  @SerialName("dewpoint_c"         ) var dewpointC        : Double?    = null,
  @SerialName("dewpoint_f"         ) var dewpointF        : Double?    = null,
  @SerialName("vis_km"             ) var visKm            : Double?    = null,
  @SerialName("vis_miles"          ) var visMiles         : Double?    = null,
  @SerialName("uv"                 ) var uv               : Double?    = null,
  @SerialName("gust_mph"           ) var gustMph          : Double?    = null,
  @SerialName("gust_kph"           ) var gustKph          : Double?    = null

)