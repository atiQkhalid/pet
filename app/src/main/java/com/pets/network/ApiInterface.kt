package com.pets.network

import com.pets.network.models.ProfileResponse
import com.pets.network.models.request.LoginDto
import com.pets.network.models.request.SignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * The ApiInterface.kt
 */
interface ApiInterface {

    @POST("api/v1/auth/register")
    fun createUser(@Body signUpDto: SignUpDto): Call<ProfileResponse>

    @POST("api/v1/auth/login")
    fun authenticate(@Body userDto: LoginDto): Call<ProfileResponse>
}
