package com.pets.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pets.activities.MainActivity
import com.pets.core.utils.DialogUtils
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * The BaseFragment.kt
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var mainActivity: MainActivity
    protected lateinit var progressDialog: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        progressDialog = DialogUtils.showProgressDialog(mainActivity, cancelable = false)
    }
}