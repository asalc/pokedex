package es.rudo.pokedex.presentation.components

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.constraintlayout.compose.ConstraintLayout
import arrow.core.valid
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import es.rudo.pokedex.App
import es.rudo.pokedex.R
import es.rudo.pokedex.presentation.theme.ColorRed
import es.rudo.pokedex.presentation.theme.PokedexTheme
import es.rudo.pokedex.presentation.theme.PopUpBackground

@Composable
fun ProgressLoader() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(PopUpBackground)
    ) {
        CircularProgressIndicator(
            strokeWidth = 4.dp,
            color = ColorRed,
            modifier = Modifier.size(60.dp)
        )
    }
}

@Composable
fun ErrorPopUp(
    message: String,
    context: Context,
    onClose: () -> Unit
) {
    var dismissDialog by remember { mutableStateOf(false) }

    if (!dismissDialog) {
        Dialog(onDismissRequest = { }) {
            Card(
                backgroundColor = Color.White,
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 50.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Button(
                        onClick = {
                            dismissDialog = true
                            onClose()
                        }
                    ) {
                        Text(text = context.getString(R.string.dismiss_dialog))
                    }
                }
            }
        }
    }
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
    showBackground = true,
    widthDp = 80,
    heightDp = 80
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
        ErrorPopUp(
            message = "Se ha producido un error",
            context = App(),
            onClose = { }
        )
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

