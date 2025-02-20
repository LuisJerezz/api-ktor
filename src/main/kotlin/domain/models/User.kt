package com.example.domain.models
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val name : String?,
    val email : String?,
    val password : String?,
    val phone : Int?,
    val urlImage : String?,
    val disponible : Boolean? = true,
    val token : String?
)
