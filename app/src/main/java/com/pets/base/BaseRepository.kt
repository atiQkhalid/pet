package com.pets.base

import com.pets.network.ApiInterface
import com.pets.network.RetrofitClient
import org.koin.java.KoinJavaComponent

abstract class BaseRepository {
    protected val retrofit : ApiInterface = RetrofitClient.getInterfaceService()
}
