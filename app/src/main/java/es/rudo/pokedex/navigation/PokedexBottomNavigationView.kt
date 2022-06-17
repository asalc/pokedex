package es.rudo.pokedex.navigation

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Text
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import es.rudo.pokedex.presentation.theme.ColorRed
import es.rudo.pokedex.presentation.theme.LightRed

@Composable
fun PokedexBottomNavigationView(
    navController: NavController,
    context: Context
) {

    val navigationItems = listOf(
        NavigationItem.Berries,
        NavigationItem.Pokemon,
        NavigationItem.Items,
        NavigationItem.Settings
    )

    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
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
                Spacer(modifier = Modifier.width(5.dp))
                BottomNavigationItem(
                    icon = {
                        Image(
                            painter = painterResource(id = item.icon),
                            contentDescription = context.getString(item.title),
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
                            shape = CircleShape.copy(CornerSize(20.dp))
                        )
                        .border(
                            border = if (selected) BorderStroke(1.dp, ColorRed)
                            else BorderStroke(0.dp, Color.White),
                            shape = CircleShape.copy(CornerSize(20.dp))
                        )
                )
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    }
}

private object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha =
        RippleAlpha(
            0.0f,
            0.0f,
            0.0f,
            0.0f
        )
}