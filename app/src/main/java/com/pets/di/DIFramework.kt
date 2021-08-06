package com.pets.di

import android.content.Context
import com.pets.core.prefences.PrefManager
import com.pets.fragments.repository.ApplicationRepository
import com.pets.network.RetrofitClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * DIFramework.kt
 * The dependency injection framework used by the app.
 * uses Koin for DI.
 */
object DIFramework {

    fun init(context: Context) {

        val repoModule = module {
            //single { RetrofitClient.getInterfaceService() }
            single { ApplicationRepository.getInstance() }
            single { PrefManager.getInstance() }
        }

        // start Koin!
        startKoin {
            // declare used Android context
            androidContext(context)
            // declare modules
            modules(repoModule)
        }
    }
}