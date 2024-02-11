package com.example.tinkoffprojectsfilms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FilmInfo(
    val nameRu: String,
    val posterUrl: String,
    val description: String,
    val countries: List<Country>,
    val genres: List<Genre>
)