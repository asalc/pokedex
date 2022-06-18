package es.rudo.pokedex

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.Configuration
import androidx.compose.ui.platform.LocalContext
import dagger.hilt.android.HiltAndroidApp
import es.rudo.domain.model.Language
import es.rudo.pokedex.helpers.AppPreferences
import java.util.*

@SuppressLint("StaticFieldLeak")
@HiltAndroidApp
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        preferences = AppPreferences(applicationContext)
        Locale.setDefault(Locale(preferences.getLanguage()))
    }

    companion object {
        lateinit var instance: App private set
        lateinit var preferences: AppPreferences
    }
}