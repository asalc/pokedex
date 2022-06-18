package es.rudo.pokedex.presentation.screens.main

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.pokedex.App
import es.rudo.pokedex.R
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
        topBar = { Toolbar() },
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
            navController = navController,
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun Toolbar() {
    TopAppBar(
        backgroundColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxSize()
        ) {
            IconButton(
                onClick = {  }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = stringResource(R.string.settings)
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DefaultPreview() {
    PokedexTheme {
        PokedexApp(LocalContext.current)
    }
}