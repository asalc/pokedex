package es.rudo.pokedex.presentation.screens.main.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import es.rudo.domain.model.Language
import es.rudo.pokedex.App
import es.rudo.pokedex.R
import es.rudo.pokedex.helpers.Utils
import es.rudo.pokedex.presentation.theme.ColorGray
import es.rudo.pokedex.presentation.theme.ColorRed
import es.rudo.pokedex.presentation.theme.PokedexTheme
import java.util.*

@AndroidEntryPoint
class SettingsActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //This finishes the parent activity of this composable
            BackHandler(enabled = true) {
                finish()
            }

            PokedexTheme {
                SettingsScreen(applicationContext)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Utils.setLocale(applicationContext, App.preferences)
    }

    @Composable
    fun SettingsScreen(
        context: Context
    ) {
        Scaffold(
            topBar = { Toolbar(context) }
        ) {
            SettingsContent(
                context = context,
                modifier = Modifier.padding(it)
            )
        }
    }

    @Composable
    fun Toolbar(
        context: Context
    ) {
        TopAppBar(
            title = {
                Text(
                    text = context.getString(R.string.settings)
                )
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        finish()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = ColorRed
                    )
                }
            },
            backgroundColor = Color.White
        )
    }

    @Composable
    fun SettingsContent(
        context: Context,
        modifier: Modifier = Modifier
    ) {
        Card(
            backgroundColor = ColorGray,
            shape = RoundedCornerShape(20.dp),
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(
                    dimensionResource(R.dimen.padding_regular)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        horizontal = dimensionResource(R.dimen.padding_regular),
                        vertical = dimensionResource(R.dimen.padding_small)
                    )
            ) {

                for (i in Language.values().indices) {
                    val language = Language.values()[i]
                    val isSelected by remember {
                        mutableStateOf(
                            language.tag == Locale.getDefault().language
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = language.label,
                            modifier = Modifier
                                .weight(1f)
                                .padding(
                                    start = dimensionResource(R.dimen.padding_small)
                                )
                        )

                        RadioButton(
                            selected = isSelected,
                            onClick = {
                                App.preferences.setLanguage(language.tag)
                                Utils.setLocale(context, App.preferences)
                                setResult(RESULT_OK)
                                finish()
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = ColorRed
                            )
                        )
                    }

                    if (i != Language.values().size - 1) {
                        Divider(
                            color = Color.Gray.copy(0.65f),
                            modifier = Modifier
                                .padding(
                                    vertical = dimensionResource(R.dimen.padding_mini)
                                )
                        )
                    }
                }
            }
        }
    }

    @Preview(
        showSystemUi = true,
        showBackground = true
    )
    @Composable
    fun SettingsScreenPreview() {
        PokedexTheme {
            SettingsScreen(LocalContext.current)
        }
    }
}