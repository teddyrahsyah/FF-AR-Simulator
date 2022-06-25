package com.develo.ff_arsimulator.base

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.util.*

open class BaseActivity: AppCompatActivity() {
    companion object {
        var dLocale: Locale? = null
        var themeMode: Boolean = false
    }

    init {
//        updateConfig(this)
//        updateThemeMode()
    }



    private fun updateConfig(wrapper: ContextThemeWrapper) {
        if (dLocale == Locale("")) return

        Log.d("Base/Language", dLocale.toString())

        Locale.setDefault(dLocale)
        val configuration = Configuration()
        configuration.setLocale(dLocale)
        wrapper.applyOverrideConfiguration(configuration)
    }

//    private fun updateThemeMode() {
//        // Checking if dark mode is on/off
//        if (themeMode == true) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES) else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
//    }
}