package com.develo.ff_arsimulator.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import com.develo.ff_arsimulator.base.MainActivity
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.databinding.ActivitySplashScreenBinding
import com.develo.ff_arsimulator.utils.AppThemeUtils

@Suppress("DEPRECATION")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        AppThemeUtils.updateTheme(sharedPreferences, delegate)

        val mode = AppCompatDelegate.getDefaultNightMode()
        if (mode == AppCompatDelegate.MODE_NIGHT_YES) {
            binding.layout.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_dark))
            binding.ivLogo.setImageResource(R.drawable.ic_arsim_logo_dark_main)
        }

        val splashTime = 1000L

        Handler().postDelayed({
            Intent(this, MainActivity::class.java).also {
                startActivity(it)
                finish()
            }
        }, splashTime)
    }
}