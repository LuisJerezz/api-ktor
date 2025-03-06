package com.example.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String,
    val email: String?
)