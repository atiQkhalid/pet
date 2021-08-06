package com.pets.core.extensions

import android.annotation.SuppressLint
import android.util.Patterns
import com.pets.core.prefences.PrefManager
import com.google.gson.Gson
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

/**
 * This method will check a string to return a valid email.
 * @return a valid email or null.
 */
fun String.getValidEmail(): String? {
    if (isNotBlank() && Patterns.EMAIL_ADDRESS.matcher(this).matches())
        return this

    return null
}

/**
 * This method will check a string to return a valid password.
 * @return a valid true or false.
 */
fun String.checkPassword(): Boolean {
    var isValid = false
    val expression =
        "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
    val pattern = Pattern.compile(expression)
    val matcher = pattern.matcher(this)
    if (matcher.matches()) {
        isValid = true
    }
    return isValid
}

/**
 * An Extension to save Object in Preferences.
 * @param dataObject as Object.
 * @param key to Map in Pref.
 * @return void
 */
fun Any?.saveDataObjectInPref(key: String) {
    val json = Gson().toJson(this)
    PrefManager.getInstance().putStringPref(key, json)
}

/**
 * An Extension to get TodayDate.
 * @return formatted date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 */
@SuppressLint("SimpleDateFormat")
fun Any.getTodayDate(): String {
    val todayDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
    return formatter.format(todayDate)
}

/**
 * An Extension to getDateInLocalZone.
 * @param string date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 * @return local date in same format
 */
fun String?.getDateInLocalZone(): String? {
    if (this == null)
        return null

    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    df.timeZone = TimeZone.getTimeZone("UTC")
    val date = df.parse(this)?: return null
    df.timeZone = TimeZone.getDefault()
    return df.format(date)
}

/**
 * An Extension to getDayAndDay.
 * @param string date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 * @return formatted date as dd-MMM HH:mm a.
 */
@SuppressLint("SimpleDateFormat")
fun String?.getDayAndDay(): String? {
    if (this == null)
        return null

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val date = sdf.parse(this)?: return null
    return SimpleDateFormat("EEEE dd").format(date)
}

/**
 * An Extension to getMonthAndYear.
 * @param string date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 * @return formatted date as MMMM yyyy.
 */
@SuppressLint("SimpleDateFormat")
fun String?.getMonthAndYear(): String? {
    if (this == null)
        return null

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val date = sdf.parse(this)?:return null
    return SimpleDateFormat("MMMM yyyy").format(date)
}


/**
 * An Extension to save Object in Preferences.
 * @param string date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 * @return formatted date as dd-MMM HH:mm a.
 */
@SuppressLint("SimpleDateFormat")
fun String?.getFormattedTime(): String? {
    if (this == null)
        return null

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS", Locale.getDefault())
    val date = sdf.parse(this)?:return null
    return SimpleDateFormat("hh:mm a").format(date)
}


/**
 * An Extension getTimeInMillis.
 * @param string date as yyyy-MM-dd'T'HH:mm:ss.SSS.
 * @return formatted date as Time in milli.
 */
fun String?.getTimeInMillis(): Long {
    //2014-10-23T00:35:14.800Z
    //2021-01-13T00:50:34.601-06:00
    if (this == null)
        return 0L

    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
    val date = sdf.parse(this)
    return date?.time ?: 0L
}