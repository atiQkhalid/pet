package com.pets.fragments.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.pets.databinding.FragmentIntro1Binding

class IntroFragment1 : Fragment() {

    private lateinit var binding: FragmentIntro1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }
}
