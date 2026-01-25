package es.shiro.pokedex.helpers

import android.content.Context
import android.content.SharedPreferences
import java.util.*

class AppPreferences(val context: Context) {

    private val PREFERENCES_FILE = "MyPreferences"
    private val LANGUAGE = "LANGUAGE"

    private fun getSharedPreferences(): SharedPreferences? {
        return context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)
    }

    fun getLanguage(): String {
        return getSharedPreferences()?.getString(LANGUAGE, null) ?: Locale.getDefault().language
    }

    fun setLanguage(language: String?) {
        this.getSharedPreferences()?.edit()?.putString(LANGUAGE, language)?.apply()
    }
}