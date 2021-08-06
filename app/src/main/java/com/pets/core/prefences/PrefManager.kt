package com.pets.core.prefences

import android.content.Context
import android.content.SharedPreferences
import com.pets.App
import com.pets.core.utils.Const.ACCESS_TOKEN
import com.pets.core.utils.Const.GOT_INTRO
import com.pets.core.utils.Const.USER_SESSION

class PrefManager(private var context: Context) {

    companion object {
        private const val SESSION_DETAILS = "SessionDetails"

        private var instance: PrefManager? = null
        fun getInstance(): PrefManager {
            if (instance == null)
                instance =
                    PrefManager(App.getAppContext())
            return instance!!
        }
    }

    var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(SESSION_DETAILS, Context.MODE_PRIVATE)

    fun putStringPref(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }


    fun getStringPref(key: String, defaultKey: String?): String? {
        return sharedPreferences.getString(key, defaultKey)
    }

    fun putIntPref(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String, defaultKey: Int): Int? {
        return sharedPreferences.getInt(key, defaultKey)
    }

    fun putBooleanPref(key: String, value: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getBoolean(
        key: String,
        defaultKey: Boolean = false,
        prefName: String = SESSION_DETAILS
    ): Boolean {
        val sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(key, defaultKey)
    }

    fun putFloatPref(key: String, value: Float) {
        val editor = sharedPreferences.edit()
        editor.putFloat(key, value)
        editor.apply()
    }

    fun getFloat(key: String, defaultKey: Float): Float {
        return sharedPreferences.getFloat(key, defaultKey)
    }

    fun deleteSpec(key: String) {
        val ed = sharedPreferences.edit()
        ed.remove(key)
        ed.apply()
    }

    fun logoutSession() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun saveSessionDetails(username: String, password: String) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_SESSION, username + password)
        editor.apply()
    }

    fun isUserLoggedOut(): Boolean {
        return sharedPreferences.getString(USER_SESSION, "")!!.isEmpty()
    }

    fun saveDouble(key: String, value: Double) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value.toString())
        editor.apply()
    }

    fun getDouble(key: String, defaultKey: Double): Double? {
        return sharedPreferences.getString(key, defaultKey.toString())?.toDouble()
    }

    fun getAccessToken(): String? {
        return getStringPref(ACCESS_TOKEN, null)
    }

    fun saveAccessToken(token: String) {
        putStringPref(ACCESS_TOKEN, token)
    }

    fun hasSeenIntro(value: Boolean = true) {
        val sharedPreferences = context.getSharedPreferences("IntroDetails", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(GOT_INTRO, value)
        editor.apply()
    }
}
