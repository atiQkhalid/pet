package com.pets.fragments.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pets.fragments.ui.intro.IntroMainFragment
import com.pets.base.BaseFragmentBeforeLogin
import com.pets.core.extensions.replaceFragment
import com.pets.core.utils.Const
import com.pets.databinding.FragmentSplashBinding
import com.pets.fragments.ui.signup.SignupFragment

class SplashFragment : BaseFragmentBeforeLogin() {

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (prefManager.isUserLoggedOut() && prefManager.getBoolean(Const.GOT_INTRO, false).not())
                replaceFragment(IntroMainFragment(), addToBackStack = false)
            else if (prefManager.isUserLoggedOut() && prefManager.getBoolean(Const.GOT_INTRO, true))
                replaceFragment(SignupFragment(), addToBackStack = false)
            else
                goToMainActivity()
        }, SPLASH_DURATION)
    }

    companion object {
        const val SPLASH_DURATION = 2000L
    }
}
