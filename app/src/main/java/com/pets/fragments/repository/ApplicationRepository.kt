package com.pets.fragments.repository

import com.pets.network.models.ProfileResponse
import com.pets.network.models.request.LoginDto
import com.pets.base.BaseRepository
import com.pets.network.models.request.SignUpDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The Repository.kt
 */
class ApplicationRepository private constructor() : BaseRepository() {

    fun createUser(
        signUpDto: SignUpDto,
        profileAttributes: (ProfileResponse?) -> Unit
    ) {
        retrofit.createUser(
            signUpDto
        ).enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>, response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        profileAttributes(it)
                    } ?: profileAttributes(null)
                } else {
                    profileAttributes(null)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                profileAttributes(null)
            }
        })
    }

    fun authenticateUser(
        loginDto: LoginDto,
        profileAttributes: (ProfileResponse?) -> Unit
    ) {
        retrofit.authenticate(
            loginDto
        ).enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>, response: Response<ProfileResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        profileAttributes(it)
                    } ?: profileAttributes(null)
                } else {
                    profileAttributes(null)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                profileAttributes(null)
            }
        })
    }

    companion object {
        private var instance: ApplicationRepository? = null
        fun getInstance(): ApplicationRepository {
            if (instance == null)
                instance =
                    ApplicationRepository()
            return instance!!
        }
    }
}