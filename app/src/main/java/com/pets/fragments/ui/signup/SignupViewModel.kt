package com.pets.fragments.ui.signup

import com.pets.network.models.ProfileResponse
import com.pets.fragments.repository.ApplicationRepository
import com.pets.base.BaseViewModel
import com.pets.core.utils.Const.USER_NAME
import com.pets.network.models.request.SignUpDto

class SignupViewModel : BaseViewModel<SignupViewModel.View, ApplicationRepository>() {

    override fun setRepo() = ApplicationRepository.getInstance()

    fun createUser(
        name: String,
        email: String,
        password: String,
        passwordConfirmation: String,
        phoneNumber: String,
        surname: String,
        acceptedUserAgreement: Boolean
    ) {
        if (name.isBlank()) {
            getView().onDetailsUpdateError("Name is required")
            return
        }
        if (surname.isBlank()) {
            getView().onDetailsUpdateError("Surname is required")
            return
        }
        if (phoneNumber.isBlank()) {
            getView().onDetailsUpdateError("Phone number is required")
            return
        }
        if (email.isBlank()) {
            getView().onDetailsUpdateError("Email is required")
            return
        }
        if (password.isBlank()) {
            getView().onDetailsUpdateError("Password is required")
            return
        }
        if (passwordConfirmation != password) {
            getView().onDetailsUpdateError("Confirmed password must be same as password")
            return
        }
        if (acceptedUserAgreement.not()) {
            getView().onDetailsUpdateError("Please accept the term of conditions")
            return
        }

        getView().showProgressBar()
        getRepo().createUser(
            SignUpDto(
                name = name,
                email = email,
                password = password,
                phoneNumber = phoneNumber,
                passwordConfirmation = passwordConfirmation,
                surname = surname,
                acceptedUserAgreement = if (acceptedUserAgreement) "1" else "0"
            )
        ) { profileAttributes ->
            getView().dismissProgressBar()
            profileAttributes?.let {
                getView().onLoginSuccess(it)
                prefManager.putStringPref(USER_NAME, it.name!!)
            } ?: getView().onDetailsUpdateError("Something went wrong")
        }
    }

    interface View {
        fun onLoginSuccess(profileAttributes: ProfileResponse)
        fun onDetailsUpdateError(error: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}