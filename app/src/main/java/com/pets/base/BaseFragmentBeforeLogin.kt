package com.pets.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.pets.fragments.ui.LoginActivity
import com.pets.activities.MainActivity
import com.pets.core.prefences.PrefManager
import com.pets.core.utils.DialogUtils
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.java.KoinJavaComponent

abstract class BaseFragmentBeforeLogin : Fragment() {

    protected lateinit var loginActivity: LoginActivity
    protected lateinit var progressDialog : KProgressHUD
    protected val prefManager: PrefManager by KoinJavaComponent.inject(
        PrefManager::class.java
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginActivity = activity as LoginActivity
        progressDialog = DialogUtils.showProgressDialog(loginActivity, "Please wait")
    }

    protected fun goToMainActivity(){
        startActivity(Intent(loginActivity, MainActivity::class.java))
        loginActivity.finish()
    }
}