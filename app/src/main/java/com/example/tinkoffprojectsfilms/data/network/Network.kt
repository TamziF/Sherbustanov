package com.example.tinkoffprojectsfilms.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

const val BASE_URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films/"

class Network {
    private val json = Json {
        ignoreUnknownKeys = true
    }

    val api: ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .build().create(ApiService::class.java)
}