package es.rudo.pokedex.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rudo.pokedex.presentation.theme.PokedexTheme

@Composable
fun ProgressLoader() {

}

@Composable
fun ErrorPopUp(message: String) {

}

@Composable
fun PageButtons(
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit
) {
    Row {
        Button(
            onClick = { onClickPrevious() },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(text = "Previous")
        }
        Button(
            onClick = { onClickNext() },
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                )
        ) {
            Text(text = "Next")
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ProgressLoaderPreview() {
    PokedexTheme {
        ProgressLoader()
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun ErrorPopUpPreview() {
    PokedexTheme {
        ErrorPopUp("Se ha producido un error")
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PagerButtonsPreview() {
    PokedexTheme {
        PageButtons(
            onClickPrevious = { },
            onClickNext = { }
        )
    }
}

