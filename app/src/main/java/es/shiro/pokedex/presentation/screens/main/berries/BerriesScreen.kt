package es.shiro.pokedex.presentation.screens.main.berries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.shiro.pokedex.presentation.theme.PokedexTheme

@Composable
fun BerriesScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "BERRIES")
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun BerriesScreenPreview() {
    PokedexTheme {
        BerriesScreen()
    }
}