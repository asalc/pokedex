package es.shiro.pokedex.navigation

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import es.shiro.pokedex.presentation.theme.ColorRed
import es.shiro.pokedex.presentation.theme.LightRed

@Composable
fun PokedexBottomNavigationView(
    navController: NavController,
    context: Context
) {

    val navigationItems = listOf(
        NavigationItem.Berries,
        NavigationItem.Pokemon,
        NavigationItem.Items
    )

    BottomNavigation(
        backgroundColor = Color.White,
        modifier = Modifier.fillMaxWidth()
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        navigationItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { destination ->
                destination.route == item.route
            } == true
            BottomNavigationItem(
                icon = {
                    Image(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(item.title),
                        modifier = Modifier.size(24.dp)
                    )
                },
                label = {
                    Text(text = context.getString(item.title))
                },
                alwaysShowLabel = true,
                selected = selected,
                selectedContentColor = ColorRed,
                unselectedContentColor = Color.Black.copy(0.75f),
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
                        restoreState = true
                    }
                },
                modifier = Modifier
                    .background(
                        color = if (selected) LightRed
                        else Color.White,
                    )
                    .clip(RectangleShape)
            )
        }
    }
}

@Preview
@Composable
fun PokedexBottomNavigationPreview() {
    PokedexBottomNavigationView(
        rememberNavController(),
        LocalContext.current
    )
}