package es.rudo.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.rudo.pokedex.presentation.screens.main.berries.BerriesScreen
import es.rudo.pokedex.presentation.screens.main.items.ItemsScreen
import es.rudo.pokedex.presentation.screens.main.pokemon.PokemonScreen

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Items.route) {
        composable(NavigationItem.Berries.route) {
            BerriesScreen()
        }
        composable(NavigationItem.Pokemon.route) {
            PokemonScreen()
        }
        composable(NavigationItem.Items.route) {
            ItemsScreen()
        }
    }
}