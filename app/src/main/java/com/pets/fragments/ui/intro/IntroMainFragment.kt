package com.pets.fragments.ui.intro

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.pets.fragments.ui.login.LoginFragment
import com.pets.base.BaseFragmentBeforeLogin
import com.pets.core.extensions.gone
import com.pets.core.extensions.replaceFragment
import com.pets.core.extensions.visible
import com.pets.core.utils.Const.GOT_INTRO
import com.pets.databinding.FragmentIntroMainBinding
import com.pets.fragments.ui.signup.SignupFragment

class IntroMainFragment : BaseFragmentBeforeLogin(), ViewPager.OnPageChangeListener {

    private lateinit var binding: FragmentIntroMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntroMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = ViewPagerAdapter(childFragmentManager)

        binding.indicator.setViewPager(binding.viewPager)
        binding.viewPager.adapter?.registerDataSetObserver(binding.indicator.dataSetObserver)
        binding.viewPager.offscreenPageLimit = 0
        binding.viewPager.addOnPageChangeListener(this)

        binding.btnGetStarted.setOnClickListener {
            prefManager.putBooleanPref(GOT_INTRO, true)
            replaceFragment(fragment = SignupFragment())
        }
    }

    class ViewPagerAdapter(fm: FragmentManager?) :
        FragmentPagerAdapter(fm!!, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> IntroFragment1()
                1 -> IntroFragment2()
                2 -> IntroFragment3()
                else -> IntroFragment1()
            }
        }

        override fun getCount(): Int {
            return 3
        }
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0, 1 -> {
                binding.btnGetStarted.gone()
            }
            2 -> {
                binding.btnGetStarted.visible()
            }
        }
    }
}
