package com.pets.fragments.ui.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pets.network.models.ProfileResponse
import com.pets.base.BaseFragmentBeforeLogin
import com.pets.core.extensions.replaceFragment
import com.pets.core.extensions.showToastMsg
import com.pets.databinding.FragmentSignupBinding
import com.pets.fragments.ui.login.LoginFragment

class SignupFragment : BaseFragmentBeforeLogin(), SignupViewModel.View {

    private val viewModel: SignupViewModel by viewModels()
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewModel) {
            attachView(this@SignupFragment)
        }

        binding.btnSignUp.setOnClickListener {
            viewModel.createUser(
                name = binding.etName.text.toString(),
                email = binding.etEmailAddress.text.toString(),
                password = binding.etPassword.text.toString(),
                passwordConfirmation = binding.etConfirmPassword.text.toString(),
                phoneNumber = binding.etPhoneNumber.text.toString(),
                surname = binding.etSurName.text.toString(),
                acceptedUserAgreement = binding.checkbox.isChecked
            )
        }
        binding.tvLoginNavigator.setOnClickListener {
            replaceFragment(LoginFragment(), addToBackStack = true)
        }
    }

    override fun onLoginSuccess(profileAttributes: ProfileResponse) {
        goToMainActivity()
    }

    override fun onDetailsUpdateError(error: String) {
        showToastMsg(error)
    }

    override fun showProgressBar() {
        progressDialog.show()
    }

    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }
}
