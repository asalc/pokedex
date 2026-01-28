package es.shiro.pokedex.helpers.extensions

import android.content.Context
import es.shiro.pokedex.R
import java.util.Locale

fun List<Pair<String, String>>?.findLanguageEntry(
    context: Context
): String = this?.firstOrNull { name ->
    name.first == Locale.getDefault().language
}?.second ?: context.getString(R.string.unknown)