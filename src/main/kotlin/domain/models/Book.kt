package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val isbn : String?,
    val name : String?,
    val description : String?,
    val image : String?
)
