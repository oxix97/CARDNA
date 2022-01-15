package org.cardna.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


fun Context.shortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replace(@IdRes frameId: Int, fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .replace(frameId, fragment, null)
        .commit()
}
