package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class UserRegister (
    val dni: String,
    val name: String,
    val email: String,
    val password: String,
    val phone: Int? = null,
    val image: String? = null,
    val disponible: Boolean? = true


)

