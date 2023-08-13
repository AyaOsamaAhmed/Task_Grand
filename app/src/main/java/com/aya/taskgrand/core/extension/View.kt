package com.aya.taskgrand.core.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

inline fun <reified T : AppCompatActivity> Fragment.castToActivity(
    callback: (T?) -> Unit,
): T? {
    return if (requireActivity() is T) {
        callback(requireActivity() as T)
        requireActivity() as T
    } else {
        callback(null)
        null
    }
}

fun Context.showAppToast(message: String?) {
    Toast.makeText(this, message ?: "", Toast.LENGTH_LONG).show()
}

fun Context.setSnackBar(view:View , msg: String?): Snackbar {
    return Snackbar.make(view , msg!!, Snackbar.LENGTH_LONG)
    //applicationContext.findViewById(android.R.id.content)
}

fun View.show(show: Boolean = true) {
    if (show) visible() else gone()
}

fun View.showInvisible(show: Boolean = true) {
    if (show) visible() else invisible()
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
    })
}



