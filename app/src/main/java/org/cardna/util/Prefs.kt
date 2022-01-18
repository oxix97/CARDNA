package org.cardna.util

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val prefNm = "Pref"
    private val prefs = context.getSharedPreferences(prefNm, MODE_PRIVATE)
    var token: String?
        get() = prefs.getString("token", null)
        set(values) {
            prefs.edit().putString("token", values).apply()
        }
}

class App : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}