package com.pets.network.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @field:SerializedName("id") val id: String,
    @field:SerializedName("name") val name: String? = null,
    @field:SerializedName("phone") val phone: String? = null
)