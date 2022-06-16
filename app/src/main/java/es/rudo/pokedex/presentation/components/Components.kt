package es.rudo.pokedex.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import es.rudo.pokedex.presentation.theme.ColorRed
import es.rudo.pokedex.presentation.theme.PokedexTheme

@Composable
fun ProgressLoader() {

}

@Composable
fun ErrorPopUp(message: String) {

}

@Composable
fun PageButtons(
    isPreviousButtonVisible: Boolean,
    isNextButtonVisible: Boolean,
    page: State<Int>,
    onClickPrevious: () -> Unit,
    onClickNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier = modifier
    ) {
        val (previousButton, pageNumber, nextButton) = createRefs()
        if (isPreviousButtonVisible) {
            OutlinedButton(
                onClick = { onClickPrevious() },
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(2.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = ColorRed
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(36.dp)
                    .constrainAs(previousButton) {
                        top.linkTo(parent.top)
                        end.linkTo(pageNumber.start)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(ColorRed),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = page.value.toString(),
            fontSize = 24.sp,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .constrainAs(pageNumber) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
        if (isNextButtonVisible) {
            OutlinedButton(
                onClick = { onClickNext() },
                shape = CircleShape,
                elevation = ButtonDefaults.elevation(2.dp),
                border = BorderStroke(
                    width = 1.dp,
                    color = ColorRed
                ),
                contentPadding = PaddingValues(0.dp),
                modifier = Modifier
                    .size(36.dp)
                    .constrainAs(nextButton) {
                        start.linkTo(pageNumber.end)
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                    }
            ) {
                Image(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(ColorRed),
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ProgressLoaderPreview() {
    PokedexTheme {
        ProgressLoader()
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ErrorPopUpPreview() {
    PokedexTheme {
        ErrorPopUp("Se ha producido un error")
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(
    showBackground = true
)
@Composable
fun PagerButtonsPreview() {
    PokedexTheme {
        PageButtons(
            isPreviousButtonVisible = true,
            isNextButtonVisible = true,
            page = mutableStateOf(100),
            onClickPrevious = { },
            onClickNext = { }
        )
    }
}

