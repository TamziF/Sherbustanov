package com.example.tinkoffprojectsfilms.data.network

import com.example.tinkoffprojectsfilms.data.model.Answer
import com.example.tinkoffprojectsfilms.data.model.FilmInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

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

    @GET("{id}")
    suspend fun loadFilmInfo(
        @Header("X-API-KEY") apiKey: String = API_KEY,
        @Header("Content-type") contentType: String = CONTENT_TYPE,
        @Path("id") id: Int
    ): Response<FilmInfo>
}