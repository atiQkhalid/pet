package com.pets.base

import android.os.Bundle
import com.pets.activities.MainActivity
import com.pets.core.utils.DialogUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.kaopiz.kprogresshud.KProgressHUD

/**
 * The BaseFragment.kt
 */
abstract class BaseBottomSheetFragment : BottomSheetDialogFragment() {

    protected lateinit var mainActivity: MainActivity
    protected lateinit var progressDialog: KProgressHUD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivity = activity as MainActivity
        progressDialog = DialogUtils.showProgressDialog(mainActivity, cancelable = false)
    }
}