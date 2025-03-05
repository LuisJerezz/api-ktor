package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UpdatedUser (
        val name: String? = null,
        val email: String? = null,
        val password: String? = null,
        val phone: Int? = null,
        val image: String? = null,
        val disponible: Boolean? = null
    )

