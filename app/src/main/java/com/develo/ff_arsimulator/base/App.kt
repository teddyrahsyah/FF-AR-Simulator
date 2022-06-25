package com.develo.ff_arsimulator.base

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import java.util.*

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        changeLanguage(sharedPreferences)
        changeDarkMode(sharedPreferences)
    }


    private fun changeLanguage(sharedPreferences: SharedPreferences) {

        val language = sharedPreferences.getString("language", "English")
        Log.d("App/Language", language.toString())
        val change = when (language) {
            "English" -> "en"
            "Bahasa Indonesia" -> "id"
            else -> ""
        }

        BaseActivity.dLocale = Locale(change)
    }

    private fun changeDarkMode(sharedPreferences: SharedPreferences) {
        val darkMode = sharedPreferences.getBoolean("theme", false)
        BaseActivity.themeMode = darkMode
    }
}