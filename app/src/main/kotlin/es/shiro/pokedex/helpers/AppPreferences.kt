package es.shiro.pokedex.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import java.util.Locale

class AppPreferences(val context: Context) {

    private val PREFERENCES_FILE = "MyPreferences"
    private val LANGUAGE = "LANGUAGE"

    private val sharedPreferences: SharedPreferences?
        get() = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

    fun getLanguage(): String =
        sharedPreferences?.getString(LANGUAGE, null) ?: Locale.getDefault().language

    fun setLanguage(language: String?) =
        sharedPreferences?.edit {
            putString(LANGUAGE, language)
        }
}