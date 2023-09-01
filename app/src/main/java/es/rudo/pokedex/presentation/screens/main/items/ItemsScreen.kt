package es.rudo.pokedex.presentation.screens.main.items

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import es.rudo.domain.model.Item
import es.rudo.domain.model.Language
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import es.rudo.pokedex.helpers.extensions.toFormattedPrice
import es.rudo.pokedex.presentation.components.ErrorPopUp
import es.rudo.pokedex.presentation.components.PageButtons
import es.rudo.pokedex.presentation.components.ProgressLoader
import es.rudo.pokedex.presentation.theme.ColorRed
import java.util.*

@Composable
fun ItemsScreen(
    context: Context,
    viewModel: ItemsViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (items, buttons) = createRefs()
        ItemsGrid(
            context = context,
            items = viewModel.itemsList,
            modifier = Modifier
                .constrainAs(items) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }
        )
        PageButtons(
            isPreviousButtonVisible = viewModel.isPreviousButtonVisible(),
            isNextButtonVisible = viewModel.isNextButtonVisible(),
            page = viewModel.page,
            onClickPrevious = {
                if (viewModel.uiState.value != UiState.Loading)
                    viewModel.previousPage()
            },
            onClickNext = {
                if (viewModel.uiState.value != UiState.Loading)
                    viewModel.nextPage()
            },
            modifier = Modifier
                .clip(CircleShape)
                .border(width = 1.25.dp, color = ColorRed, shape = CircleShape)
                .background(Color.White)
                .padding(dimensionResource(R.dimen.padding_regular))
                .constrainAs(buttons) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(
                        anchor = parent.bottom,
                        margin = 16.dp
                    )
                }
        )

        AnimatedVisibility(
            visible = viewModel.uiState.value
                    is UiState.Loading,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ProgressLoader()
        }

        AnimatedVisibility(
            visible = viewModel.uiState.value
                    is UiState.Error
        ) {
            ErrorPopUp(
                message = context.getString(R.string.network_error),
                closeText = context.getString(R.string.dismiss_dialog)
            ) {
                if (viewModel.page.value > 1)
                    viewModel.previousPage()
            }
        }
    }
}

@Composable
fun ItemsGrid(
    context: Context,
    items: List<Item>,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            top = dimensionResource(R.dimen.padding_regular),
            start = dimensionResource(R.dimen.padding_regular),
            end = dimensionResource(R.dimen.padding_regular),
            bottom = dimensionResource(R.dimen.padding_bottom_grid)
        ),
        horizontalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_mid_small)
        ),
        verticalArrangement = Arrangement.spacedBy(
            dimensionResource(R.dimen.padding_mid_small)
        ),
        modifier = modifier
    ) {
        items(items = items, key = { it.id }) { item ->
            ItemCard(
                context = context,
                item = item
            )
        }
    }
}

@Composable
fun ItemCard(
    context: Context,
    item: Item
) {
    val itemName: String = item.name.firstOrNull { name ->
        name.first == Locale.getDefault().language
    }?.second ?: context.getString(R.string.unknown)
    val itemCost: String = item.cost.toString().toFormattedPrice()
    Card(
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.corner_radius_small)
        ),
        elevation = dimensionResource(R.dimen.elevation_small),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(
                    horizontal = dimensionResource(R.dimen.padding_small),
                    vertical = dimensionResource(R.dimen.padding_regular)
                )
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = item.sprite,
                    error = painterResource(id = R.drawable.ic_error)
                ),
                contentDescription = itemName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            )

            Text(
                text = itemName,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${if (item.cost == 0) "---" else itemCost} ¥",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ItemsScreenPreview() {
    val items: ArrayList<Item> = ArrayList()
    for (i in 0..9) {
        items.add(
            Item(
                id = i,
                name = arrayListOf(
                    Pair(Language.SPANISH.tag, "Master Ball")
                ),
                2000,
                ""
            )
        )
    }
    ItemsGrid(
        context = LocalContext.current,
        items = items
    )
}
