package com.develo.ff_arsimulator.ui.appinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import com.develo.ff_arsimulator.R
import com.develo.ff_arsimulator.databinding.ActivityAppInfoBinding

class AppInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAppInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mode = AppCompatDelegate.getDefaultNightMode()
        when (mode) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.layout.setBackgroundColor(ContextCompat.getColor(this, R.color.primary_dark))
                binding.ivAppLogo.setImageResource(R.drawable.ic_arsim_logo_dark_main)
            }
            else -> {}
        }
    }
}