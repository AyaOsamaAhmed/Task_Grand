package com.aya.taskgrand.core.extension

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.aya.taskgrand.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun Int.localize(context: Context): String = try {
    context.resources.getString(this)
} catch (e: Exception) {
    toString()
}

fun Context.showErrorDialog(message: String?, onClickCallback: () -> Unit = {}) {
    MaterialAlertDialogBuilder(this)
        .setTitle(getString(R.string.error))
        .setMessage(message)
        .setPositiveButton(getString(R.string.ok)) { dialog, _ ->
            dialog.dismiss()
            onClickCallback.invoke()
        }
        .show()
}



fun Context.getDrawableFromRes(@DrawableRes drawable: Int): Drawable? =
    ContextCompat.getDrawable(this, drawable)

fun Context.getColorFromRes(@ColorRes colorRes: Int): Int {
    return ContextCompat.getColor(this, colorRes)
}
/*
fun Context.locationAutoComplete(launcher: ActivityResultLauncher<Intent>) {
    if (!Places.isInitialized()) {
        Places.initialize(applicationContext, getString(R.string.google_maps_api), Locale.US);
    }
    val fields = listOf(Place.Field.ID, Place.Field.NAME,Place.Field.ADDRESS_COMPONENTS, Place.Field.LAT_LNG)
    val searchIntent = Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
        .build(this)
    launcher.launch(searchIntent)

}
*/

