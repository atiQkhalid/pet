package com.pets.fragments.ui

import android.os.Bundle
import com.pets.fragments.ui.login.LoginFragment
import com.pets.fragments.ui.splash.SplashFragment
import com.pets.base.BaseActivity
import com.pets.core.extensions.replaceFragmentSafely
import com.pets.core.utils.Const
import com.pets.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding
            .inflate(layoutInflater).also {
            setContentView(it.root)
        }

        if (Const.isLogoutCalled)
            replaceFragmentSafely(LoginFragment())
        else
            replaceFragmentSafely(SplashFragment())
    }
}