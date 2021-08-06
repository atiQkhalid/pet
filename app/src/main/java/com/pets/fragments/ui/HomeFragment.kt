package com.pets.fragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pets.ProfileInfoFragment
import com.pets.R
import com.pets.base.BaseFragment
import com.pets.core.extensions.replaceFragment
import com.pets.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(), View.OnClickListener {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            this.skip.setOnClickListener(this@HomeFragment)
            this.btnNext.setOnClickListener(this@HomeFragment)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.skip -> replaceFragment(ProfileInfoFragment())
            R.id.btnNext -> replaceFragment(ProfileInfoFragment())
        }
    }
}