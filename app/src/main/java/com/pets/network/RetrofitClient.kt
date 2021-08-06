package com.pets.network

import com.pets.core.prefences.PrefManager
import com.pets.core.utils.Const.BASE_URL
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.java.KoinJavaComponent.inject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * The RetrofitClient.kt
 */

object RetrofitClient {

    private val REQUEST_TIMEOUT = 60
    private var okHttpClient: OkHttpClient? = null
    private val prefManager: PrefManager by inject(PrefManager::class.java)

    fun getInterfaceService(baseUrl: String = BASE_URL): ApiInterface {
        initOkHttp()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient!!)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ApiInterface::class.java)
    }

    private fun initOkHttp() {
        val httpClient = OkHttpClient().newBuilder()
            .connectTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT.toLong(), TimeUnit.SECONDS)

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
            prefManager.getAccessToken()?.run {
                requestBuilder.addHeader("Authorization",  this)
            }

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        httpClient.addInterceptor(interceptor)
        okHttpClient = httpClient.build()
    }
}
