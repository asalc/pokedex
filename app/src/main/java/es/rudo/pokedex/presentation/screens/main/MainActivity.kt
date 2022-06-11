package es.rudo.pokedex.presentation.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.domain.use_cases.items.GetItemsUseCase
import es.rudo.pokedex.navigation.NavigationItem
import es.rudo.pokedex.navigation.PokedexBottomNavigationView
import es.rudo.pokedex.presentation.screens.main.items.ItemsList
import es.rudo.pokedex.presentation.screens.main.items.ItemsScreen
import es.rudo.pokedex.presentation.screens.main.items.ItemsScreenPreview
import es.rudo.pokedex.presentation.theme.ColorRed
import es.rudo.pokedex.presentation.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //This changes the status bar color
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(color = ColorRed)
            }

            //This finishes the parent activity of this composable
            BackHandler(enabled = true) {
                finish()
            }

            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PokedexApp()
                }
            }
        }
    }
}

@Composable
fun PokedexApp() {

    Scaffold(
        bottomBar = { PokedexBottomNavigationView() },
        modifier = Modifier.fillMaxSize()
    ) {
        ItemsScreen(
            modifier = Modifier.padding(it)
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    PokedexApp()
}