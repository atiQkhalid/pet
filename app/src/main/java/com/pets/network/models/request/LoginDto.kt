package com.pets.network.models.request

import com.google.gson.annotations.SerializedName

data class LoginDto(
    @field:SerializedName("email") val email: String,
    @field:SerializedName("password") val password: String
)
