package com.pets.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pets.R
import com.pets.databinding.LayoutInternetMessageBinding
import com.pets.core.extensions.gone
import com.pets.core.extensions.showDialogue
import com.pets.core.extensions.visible
import com.pets.core.prefences.PrefManager
import com.pets.core.utils.DialogUtils
import com.pets.core.utils.InternetMonitor
import com.kaopiz.kprogresshud.KProgressHUD
import org.koin.java.KoinJavaComponent

abstract class BaseActivity : AppCompatActivity() {

    protected lateinit var progressDialog: KProgressHUD
    private lateinit var internetMessageBinding: LayoutInternetMessageBinding
    protected val prefManager: PrefManager by KoinJavaComponent.inject(PrefManager::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        internetMessageBinding = LayoutInternetMessageBinding.inflate(layoutInflater)

        progressDialog = DialogUtils.showProgressDialog(this, cancelable = false)
    }

    private val internetMonitor = InternetMonitor(object :
        InternetMonitor.OnInternetConnectivityListener {
        override fun onInternetAvailable() {
            runOnUiThread {
                internetMessageBinding.topConnectionShower.gone()
            }
        }

        override fun onInternetLost() {
            runOnUiThread {
                internetMessageBinding.topConnectionShower.visible()
            }
        }
    })

    override fun onResume() {
        super.onResume()
        internetMonitor.register(this)
    }

    override fun onPause() {
        super.onPause()
        internetMonitor.unregister(this)
    }
}