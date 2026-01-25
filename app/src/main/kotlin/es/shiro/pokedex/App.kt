package es.shiro.pokedex

import android.annotation.SuppressLint
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import es.shiro.pokedex.helpers.AppPreferences
import es.shiro.pokedex.helpers.Utils

@SuppressLint("StaticFieldLeak")
@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = AppPreferences(applicationContext)
        preferences.setLanguage(preferences.getLanguage())
        Utils.setLocale(applicationContext, preferences)
    }

    companion object {
        lateinit var instance: App private set
        lateinit var preferences: AppPreferences
    }
}