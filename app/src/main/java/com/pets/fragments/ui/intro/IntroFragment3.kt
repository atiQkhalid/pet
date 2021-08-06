package com.pets.fragments.ui.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pets.databinding.FragmentIntro3Binding

class IntroFragment3 : Fragment() {

    private lateinit var binding: FragmentIntro3Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntro3Binding.inflate(inflater, container, false)
        return binding.root
    }
}
