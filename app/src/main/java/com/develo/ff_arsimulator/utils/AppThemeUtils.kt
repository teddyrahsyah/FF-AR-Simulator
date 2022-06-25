package com.develo.ff_arsimulator.utils

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

object AppThemeUtils {
    fun updateTheme(sharedPreferences: SharedPreferences, delegate: AppCompatDelegate) {
        val darkMode = sharedPreferences.getBoolean("theme", false)
        when (darkMode) {
            true -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            false -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }
    }
}