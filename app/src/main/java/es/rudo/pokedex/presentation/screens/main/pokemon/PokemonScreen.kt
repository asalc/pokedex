package es.rudo.pokedex.presentation.screens.main.pokemon

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import es.rudo.pokedex.presentation.theme.PokedexTheme

@Composable
fun PokemonScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "POKEMON")
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PokemonScreenPreview() {
    PokedexTheme {
        PokemonScreen()
    }
}