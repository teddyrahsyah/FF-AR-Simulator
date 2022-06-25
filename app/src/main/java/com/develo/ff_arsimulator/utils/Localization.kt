package com.develo.ff_arsimulator.utils

import android.content.Context
import android.os.Build
import android.os.LocaleList
import android.view.ContextThemeWrapper
import androidx.annotation.RequiresApi
import com.develo.ff_arsimulator.R
import java.util.*

class Localization(base: Context) : ContextThemeWrapper(base, R.style.Theme_FFARSimulator) {
    companion object {

        fun wrap(context: Context, language: String): ContextThemeWrapper {
            var ctx = context
            val config = context.resources.configuration

            if (language != "" || language != "en") {
                val locale = Locale(language)
                Locale.setDefault(locale)
                //Using setLocale b/c my version is > 17
                config.setLocale(locale)
                // Used setLayoutDirection for RTL and LTR
                config.setLayoutDirection(locale)
                ctx = context.createConfigurationContext(config)
            }

            //Save the selected language in shared Preference,
            //context.putString("my_lang", language)
            return Localization(ctx)
        }
    }
}