package es.shiro.pokedex.helpers

import android.content.Context
import android.content.res.Configuration
import java.util.*

object Utils {

    fun setLocale(context: Context, preferences: AppPreferences) =
        updateResources(context, preferences.getLanguage())

    private fun updateResources(context: Context, language: String) {
        context.resources.apply {
            val locale = Locale(language)
            val config = Configuration(configuration)

            context.createConfigurationContext(configuration)
            Locale.setDefault(locale)
            config.setLocale(locale)
            updateConfiguration(config, displayMetrics)
        }
    }
}