package com.pets.activities

import android.os.Bundle
import com.pets.R
import com.pets.base.BaseActivity
import com.pets.databinding.ActivityMainBinding
import com.pets.core.extensions.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pets.fragments.ui.HomeFragment

/**
 * MainActivity.kt, Application class
 */

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        replaceFragmentSafely(HomeFragment())
//        binding.navigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {

                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_schedule -> {

                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_profile -> {

                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }

    fun logout() {
        showDialogue(
            message = getString(R.string.signOutMessage),
            negativeButtonTitle = getString(R.string.cancel)
        ) {

        }
    }

//    fun enableBadge() {
//        val badge = binding.navigationView.getOrCreateBadge(R.id.nav_schedule)
//        badge.isVisible = true
//    }
//
//    fun disableBadge() {
//        val badge = binding.navigationView.getOrCreateBadge(R.id.nav_schedule)
//        badge.isVisible = false
//    }
//
//    fun updateNavigationViewVisibility(visible: Boolean) {
//        if (visible)
//            binding.navigationView.visible()
//        else
//            binding.navigationView.gone()
//    }
}