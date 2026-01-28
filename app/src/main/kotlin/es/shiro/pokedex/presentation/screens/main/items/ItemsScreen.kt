package es.shiro.pokedex.presentation.screens.main.items

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import es.shiro.pokedex.R
import es.shiro.pokedex.UiState
import es.shiro.pokedex.common.extensions.toFormattedPrice
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.model.Language
import es.shiro.pokedex.helpers.extensions.findLanguageEntry
import es.shiro.pokedex.presentation.components.ErrorPopUp
import es.shiro.pokedex.presentation.components.PageButtons
import es.shiro.pokedex.presentation.components.PokedexGrid
import es.shiro.pokedex.presentation.components.ProgressLoader

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
                if (viewModel.page.intValue > 1)
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
    PokedexGrid(
        items = items,
        modifier = modifier
    ) { item ->
        ItemCard(
            context = context,
            item = item
        )
    }
}

@Composable
fun ItemCard(
    context: Context,
    item: Item
) {
    val itemName: String =
        item.names.findLanguageEntry(context)

    val itemCost: String =
        item.cost.toString().toFormattedPrice()

    Card(
        shape = RoundedCornerShape(
            dimensionResource(R.dimen.corner_radius_small)
        ),
        elevation = dimensionResource(R.dimen.elevation_small),
        modifier = Modifier.fillMaxSize()
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
    val items: ArrayList<Item> = arrayListOf()
    for (i in 0..9) {
        items.add(
            Item(
                id = i,
                names = arrayListOf(
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
