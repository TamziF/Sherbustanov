package com.example.tinkoffprojectsfilms.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val films: List<Film>
)