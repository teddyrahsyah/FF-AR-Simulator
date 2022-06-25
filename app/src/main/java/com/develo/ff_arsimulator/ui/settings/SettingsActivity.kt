package com.develo.ff_arsimulator.ui.settings

import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.base.BaseActivity
import com.develo.ff_arsimulator.databinding.ActivitySettingsBinding
import com.develo.ff_arsimulator.utils.Localization
import java.util.*

@Suppress("DEPRECATION")
class SettingsActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.settings, SettingsFragment())
                .commit()
        }
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.menu_settings)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Register Settings Preferences
        PreferenceManager.getDefaultSharedPreferences(this)
            .registerOnSharedPreferenceChangeListener(this)
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey)
        }
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        when (key) {
            "theme" -> {
                val darkMode = sharedPreferences?.getBoolean(key, false)
                // Checking if dark mode is on/off
                if (darkMode == true) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
            "language" -> {
                when (sharedPreferences?.getString(key, "English")) {
                    "English" -> {
                        Localization.wrap(this, "en")
//                        setLocale("en")
                        finish()
                        startActivity(intent)
                    }
                    "Bahasa Indonesia" -> {
                        Localization.wrap(this, "id")
//                        setLocale("id")
                        finish()
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun setLocale(localeName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            val localeListToSet = LocaleList(Locale(localeName))
            LocaleList.setDefault(localeListToSet)
            resources.configuration.setLocales(localeListToSet)
        }
        resources.updateConfiguration(resources.configuration, resources.displayMetrics)
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
            .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}