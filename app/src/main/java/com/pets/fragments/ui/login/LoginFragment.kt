package com.pets.fragments.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pets.R
import com.pets.network.models.ProfileResponse
import com.pets.base.BaseFragmentBeforeLogin
import com.pets.core.extensions.showDialogue
import com.pets.databinding.FragmentLoginBinding

class LoginFragment : BaseFragmentBeforeLogin(), LoginViewModel.View, View.OnClickListener {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(viewModel) {
            attachView(this@LoginFragment)
        }
        prefManager.logoutSession()

        binding.btnSignIn.setOnClickListener(this)
    }

    override fun onLoginSuccess(profileAttributes: ProfileResponse) {
        goToMainActivity()
    }

    override fun onDetailsUpdateError(error: String) {
        loginActivity.showDialogue(
            title = getString(R.string.app_name),
            message = error,
            negativeButtonTitle= getString(R.string.no)
        ){
            if (it)
                goToMainActivity()
        }
    }

    override fun showProgressBar() {
        progressDialog.show()
    }

    override fun dismissProgressBar() {
        progressDialog.dismiss()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSignIn -> {
                viewModel.authenticateUser(
                    binding.etEmailAddress.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
    }
}
