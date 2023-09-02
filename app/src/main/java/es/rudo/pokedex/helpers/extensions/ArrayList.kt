package es.rudo.pokedex.helpers.extensions

import android.content.Context
import es.rudo.pokedex.R
import java.util.Locale

fun ArrayList<Pair<String, String>>?.findLanguageEntry(
    context: Context
): String = this?.firstOrNull { name ->
    name.first == Locale.getDefault().language
}?.second ?: context.getString(R.string.unknown)