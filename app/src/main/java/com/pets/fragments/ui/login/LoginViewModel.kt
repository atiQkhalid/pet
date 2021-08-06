package com.pets.fragments.ui.login

import com.pets.network.models.ProfileResponse
import com.pets.fragments.repository.ApplicationRepository
import com.pets.base.BaseViewModel
import com.pets.core.utils.Const.USER_NAME
import com.pets.network.models.request.LoginDto

class LoginViewModel : BaseViewModel<LoginViewModel.View, ApplicationRepository>() {

    override fun setRepo(): ApplicationRepository = ApplicationRepository.getInstance()

    fun authenticateUser(email: String, password: String) {
        if (email.isBlank()){
            getView().onDetailsUpdateError("Email is required")
            return
        }
        if (password.isBlank()){
            getView().onDetailsUpdateError("Password is required")
            return
        }

        getView().showProgressBar()
        getRepo().authenticateUser(
            LoginDto(
                email = email,
                password = password
            )
        ) { profileAttributes ->
            getView().dismissProgressBar()
            profileAttributes?.let {
                getView().onLoginSuccess(it)
                prefManager.putStringPref(USER_NAME, it.name!!)
            } ?: getView().onDetailsUpdateError("API is not working, want to proceed without auth?")
        }
    }

    interface View {
        fun onLoginSuccess(profileAttributes: ProfileResponse)
        fun onDetailsUpdateError(error: String)
        fun showProgressBar()
        fun dismissProgressBar()
    }
}