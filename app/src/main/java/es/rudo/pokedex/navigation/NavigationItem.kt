package es.rudo.pokedex.navigation

import es.rudo.pokedex.R

sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String
) {
    object Berries: NavigationItem("berries", R.drawable.ic_berry, "Berries")
    object Pokemon: NavigationItem("pokemon", R.drawable.ic_pokeball, "Pokémon")
    object Items: NavigationItem("items", R.drawable.ic_potion, "Items")
}
