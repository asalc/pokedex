package es.rudo.pokedex.presentation.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.pokedex.navigation.Navigation
import es.rudo.pokedex.navigation.PokedexBottomNavigationView
import es.rudo.pokedex.presentation.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //This changes the status bar color
            val systemUiController = rememberSystemUiController()
            SideEffect {
                systemUiController.setStatusBarColor(color = Color.White)
            }

            //This finishes the parent activity of this composable
            BackHandler(enabled = true) {
                finish()
            }

            PokedexTheme {
                PokedexApp(this)
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PokedexApp(
    context: Context
) {

    val navController = rememberNavController()
    
    Scaffold(
        bottomBar = {
            PokedexBottomNavigationView(
                navController = navController,
                context = context
            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = Color.LightGray.copy(0.15f)
    ) {
        Navigation(
            context = context,
            navController = navController,
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
    PokedexApp(MainActivity())
}