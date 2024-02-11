package com.example.tinkoffprojectsfilms.data.repository

import com.example.tinkoffprojectsfilms.data.model.Answer
import com.example.tinkoffprojectsfilms.data.network.ApiService
import retrofit2.Response

class NetworkRepository(
    private val api: ApiService
) {

    suspend fun loadFilms(): Response<Answer> {
        return api.loadFilms()
    }
}