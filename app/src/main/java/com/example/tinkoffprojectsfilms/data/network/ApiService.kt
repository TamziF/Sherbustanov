package com.example.tinkoffprojectsfilms.data.network

import com.example.tinkoffprojectsfilms.data.model.Answer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

const val API_KEY: String = "37cd426a-556e-4ffc-a33f-0977b74861de"
const val CONTENT_TYPE: String = "application/json"
const val TYPE: String = "TOP_100_POPULAR_FILMS"

interface ApiService {

    @GET("top")
    suspend fun loadFilms(
        @Header("X-API-KEY") apiKey: String = API_KEY,
        @Header("Content-type") contentType: String = CONTENT_TYPE,
        @Header("type") type: String = TYPE
    ): Response<Answer>

}