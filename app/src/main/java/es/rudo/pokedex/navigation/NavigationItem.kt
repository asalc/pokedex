package es.rudo.pokedex.navigation

import es.rudo.pokedex.R

sealed class NavigationItem(
    var route: String,
    var icon: Int
) {
    object Berries: NavigationItem("berries", R.drawable.ic_berry)
    object Pokemon: NavigationItem("pokemon", R.drawable.ic_pokeball)
    object Items: NavigationItem("items", R.drawable.ic_potion)
}
