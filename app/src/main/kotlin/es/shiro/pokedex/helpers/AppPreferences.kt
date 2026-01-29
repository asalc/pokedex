package es.shiro.pokedex.helpers

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import es.shiro.pokedex.domain.model.Language

class AppPreferences(val context: Context) {

    private val PREFERENCES_FILE = "MyPreferences"
    private val LANGUAGE = "LANGUAGE"

    private val sharedPreferences: SharedPreferences?
        get() = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

    fun getLanguage(): String =
        sharedPreferences?.getString(LANGUAGE, null) ?: Language.ENGLISH.tag

    fun setLanguage(language: String?) =
        sharedPreferences?.edit(commit = true) {
            putString(LANGUAGE, language)
        }
}