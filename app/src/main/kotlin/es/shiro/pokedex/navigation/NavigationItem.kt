package es.shiro.pokedex.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import es.shiro.pokedex.R

sealed class NavigationItem(
    var route: String,
    @DrawableRes val icon: Int,
    @StringRes val title: Int
) {
    object Pokemon: NavigationItem(
        route = "pokemon",
        icon = R.drawable.ic_pokeball,
        title = R.string.pokemon
    )
    object Items: NavigationItem(
        route = "items",
        icon = R.drawable.ic_potion,
        title = R.string.items
    )
}
