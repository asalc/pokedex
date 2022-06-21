package es.rudo.pokedex.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.rudo.pokedex.presentation.screens.main.berries.BerriesScreen
import es.rudo.pokedex.presentation.screens.main.items.ItemsScreen
import es.rudo.pokedex.presentation.screens.main.pokemon.PokemonScreen

@Composable
fun Navigation(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Items.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Berries.route) {
            BerriesScreen()
        }
        composable(NavigationItem.Pokemon.route) {
            PokemonScreen()
        }
        composable(NavigationItem.Items.route) {
            ItemsScreen(context)
        }
    }
}