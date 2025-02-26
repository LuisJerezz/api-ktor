package com.example.domain.models
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val dni : String?,
    val name : String?,
    val email : String?,
    val password : String?,
    val phone : Int?,
    val image : String?,
    val disponible : Boolean? = true,
    val token : String?
)
