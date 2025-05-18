package com.example.androidcourse.network

import com.example.androidcourse.network.model.City
import com.example.androidcourse.network.model.Weather
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.weatherapi.com/v1/"
private const val API_KEY = "72860e139b9643c3a80120932251004"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q")
        city: String = "Izhevsk",
        @Query("days")
        days: String = "5",
        @Query("key")
        key: String = API_KEY,
        @Query("aqi")
        aqi: String = "no",
        @Query("alerts")
        alerts: String = "no",
    ): Weather
    @GET("search.json")
    suspend fun getCities(
        @Query("q")
        city: String,
        @Query("key")
        key: String = API_KEY
    ) : List<City>
}

object WeatherApi {
    val retrofitService: WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}