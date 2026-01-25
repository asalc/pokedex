package es.shiro.pokedex.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import es.shiro.pokedex.presentation.screens.main.items.ItemsScreen
import es.shiro.pokedex.presentation.screens.main.pokemon.PokemonScreen

@Composable
fun Navigation(
    navController: NavHostController,
    context: Context,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = NavigationItem.Pokemon.route,
        modifier = modifier
    ) {
        composable(NavigationItem.Pokemon.route) {
            PokemonScreen(context)
        }
        composable(NavigationItem.Items.route) {
            ItemsScreen(context)
        }
    }
}