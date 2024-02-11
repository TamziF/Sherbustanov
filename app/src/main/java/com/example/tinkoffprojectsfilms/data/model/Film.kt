package com.example.tinkoffprojectsfilms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Film(
    val filmId: Int,
    val nameRu: String,
    val year: String,
    val genres: List<Genre>,
    val posterUrlPreview: String
)