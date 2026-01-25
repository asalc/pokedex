package es.shiro.pokedex.presentation.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.constraintlayout.compose.ConstraintLayout
import es.shiro.pokedex.R
import es.shiro.pokedex.presentation.theme.ColorRed
import es.shiro.pokedex.presentation.theme.PokedexTheme

@Composable
fun ProgressLoader() {

    Dialog(
        onDismissRequest = { }
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
    closeText: String,
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
                    .height(150.dp)
                    .padding(horizontal = 50.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.75f)
                            .padding(16.dp)
                    ) {
                        Text(
                            text = message,
                            textAlign = TextAlign.Center
                        )
                    }
                    Button(
                        onClick = {
                            dismissDialog = true
                            onClose()
                        },
                        shape = RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 12.dp,
                            bottomEnd = 12.dp
                        ),
                        colors = ButtonDefaults.outlinedButtonColors(
                            backgroundColor = ColorRed
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.25f)
                    ) {
                        Text(
                            text = closeText,
                            fontWeight = FontWeight.Normal,
                            color = Color.White
                        )
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
            .clip(CircleShape)
            .border(width = 1.25.dp, color = ColorRed, shape = CircleShape)
            .background(Color.White)
    ) {
        val (previousButton, pageNumber, nextButton) = createRefs()
        if (isPreviousButtonVisible) {
            Button(
                onClick = { onClickPrevious() },
                shape = RoundedCornerShape(
                    topEndPercent = 0,
                    bottomEndPercent = 0
                ),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ColorRed),
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
                    colorFilter = ColorFilter.tint(Color.White),
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
            Button(
                onClick = { onClickNext() },
                shape = RoundedCornerShape(
                    topStartPercent = 0,
                    bottomStartPercent = 0
                ),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = ColorRed),
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
                    colorFilter = ColorFilter.tint(Color.White),
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
            closeText = "Cerrar",
            onClose = { }
        )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PagerButtonsPreview() {
    PokedexTheme {
        PageButtons(
            isPreviousButtonVisible = true,
            isNextButtonVisible = true,
            page = remember { mutableIntStateOf(100) },
            onClickPrevious = { },
            onClickNext = { }
        )
    }
}

