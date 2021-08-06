package com.pets.network.models.request

import com.google.gson.annotations.SerializedName

data class SignUpDto(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("email") val email: String,
    @field:SerializedName("password") val password: String,
    @field:SerializedName("password_confirmation") val passwordConfirmation: String,
    @field:SerializedName("phone_number") val phoneNumber: String,
    @field:SerializedName("district_id") val districtId: Int = 1, //
    @field:SerializedName("city_id") val cityId: Int = 1, //
    @field:SerializedName("surname") val surname: String,
    @field:SerializedName("is_phone_number_hidden") val is_phoneNumberHidden: String = "1", //
    @field:SerializedName("accepted_user_agreement") val acceptedUserAgreement: String,
)
