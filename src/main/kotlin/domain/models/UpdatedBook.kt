package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdatedBook(
    val name: String? = null,
    val description: String? = null,
    val image: String? = null
)