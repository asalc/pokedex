package es.shiro.pokedex.presentation.screens.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import es.shiro.pokedex.R
import es.shiro.pokedex.navigation.Navigation
import es.shiro.pokedex.navigation.PokedexBottomNavigationView
import es.shiro.pokedex.presentation.screens.main.settings.SettingsActivity
import es.shiro.pokedex.presentation.theme.PokedexTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            //This finishes the parent activity of this composable
            BackHandler(enabled = true) {
                finish()
            }

            PokedexTheme {
                PokedexApp(applicationContext)
            }

        }
    }

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
            modifier = Modifier.fillMaxSize().navigationBarsPadding(),
            backgroundColor = Color.LightGray.copy(0.15f)
        ) {
            Navigation(
                navController = navController,
                context = context,
                modifier = Modifier.padding(it)
            )
        }
    }

    @Composable
    fun Toolbar(modifier: Modifier = Modifier) {
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
            onResult = {
                when (it.resultCode) {
                    RESULT_OK -> {
                        recreate()
                    }
                }
            }
        )
        TopAppBar(
            backgroundColor = Color.White
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxSize()
            ) {

                Spacer(modifier = modifier.weight(1.8f))

                Image(
                    painter = painterResource(
                        id = R.drawable.pokedex_title_logo
                    ),
                    contentDescription = null,
                    modifier = Modifier
                        .height(
                            dimensionResource(
                                id = R.dimen.pokedex_title_height
                            )
                        )
                        .wrapContentWidth()
                )

                Spacer(modifier = modifier.weight(1.0f))

                IconButton(
                    onClick = {
                        launcher.launch(
                            Intent(
                                this@MainActivity,
                                SettingsActivity::class.java
                            )
                        )
                    }
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
    fun PokedexAppPreview() {
        PokedexTheme {
            PokedexApp(this)
        }
    }
}