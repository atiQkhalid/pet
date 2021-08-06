package com.pets.core.extensions

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.pets.App
import com.pets.R

/**
 * Extension function to show toast message
 * @return void
 */
fun Any.showToastMsg(message: String) {
    Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()
}

/**
 * Extension function to show  dialog
 * @return void
 */
fun Activity.showDialogue(
    title: String? = null,
    message: String? = null,
    drawable: Int? = null,
    cancelable: Boolean = true,
    positiveButtonTitle: String = getString(R.string.ok),
    negativeButtonTitle: String? = null,
    onClick: ((Boolean) -> Unit)
) {
    val builder = AlertDialog.Builder(this)
    builder.apply {
        setTitle(title)
        setMessage(message)
        setCancelable(cancelable)
        if (drawable != null)
            builder.setIcon(drawable)

        setPositiveButton(positiveButtonTitle) { dialog, _ ->
            onClick(true)
            dialog.dismiss()
        }

        if (negativeButtonTitle.isNullOrBlank().not()) {
            builder.setNegativeButton(negativeButtonTitle) { dialog, _ ->
                onClick(false)
                dialog.dismiss()
            }
        }
        show()
    }
}

/**
 * An Extension to make view Visible
 * @return void
 */
fun View.visible() {
    visibility = View.VISIBLE
}

/**
 * An Extension to make view Gone
 * @return void
 */
fun View.gone() {
    visibility = View.GONE
}

/**
 * An Extension to close keyboard.
 * @return void
 */
fun View.closeKeyboard() {
    val inputMethodManager =
        this.context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

/**
 * An Extension to getColorCompat of view via context
 * @return void
 */
fun Context.getColorCompat(@ColorRes colorRes: Int) = ContextCompat.getColor(this, colorRes)

/**
 * An Extension to getColorCompat via fragment
 * @return void
 */
fun Fragment.getColor(@ColorRes colorRes: Int) = requireContext().getColorCompat(colorRes)