package es.rudo.pokedex.presentation.screens.main.pokemon

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import es.rudo.domain.model.Language
import es.rudo.domain.model.Pokemon
import es.rudo.domain.model.PokemonType
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import es.rudo.pokedex.presentation.components.ErrorPopUp
import es.rudo.pokedex.presentation.components.PageButtons
import es.rudo.pokedex.presentation.components.ProgressLoader
import es.rudo.pokedex.presentation.theme.ColorRed
import java.util.Locale

private const val POKEMON_TYPE_PREFIX = "ic_type_"

@Composable
fun PokemonScreen(
    context: Context,
    viewModel: PokemonViewModel = hiltViewModel()
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (pokemon, buttons) = createRefs()
        PokemonGrid(
            context = context,
            pokemon = viewModel.pokemonList,
            modifier = Modifier
                .constrainAs(pokemon) {
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
                if (viewModel.page.intValue > 1)
                    viewModel.previousPage()
            }
        }
    }
}

@Composable
fun PokemonGrid(
    context: Context,
    pokemon: List<Pokemon>,
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
        items(items = pokemon, key = { it.id }) { pokemon ->
            PokemonCard(
                context = context,
                pokemon = pokemon
            )
        }
    }
}

@Composable
fun PokemonCard(
    context: Context,
    pokemon: Pokemon
) {
    val pokemonName: String = pokemon.names?.firstOrNull { name ->
        name.first == Locale.getDefault().language
    }?.second ?: context.getString(R.string.unknown)
    val pokemonTypes: ArrayList<Int>? =
        pokemon.types?.distinct()?.map { type ->
            PokemonType.values().find {
                it.label == type?.label
            }?.let {
                context.resources.getIdentifier(
                    POKEMON_TYPE_PREFIX + it.label,
                    "drawable",
                    context.packageName
                )
            } ?: 0
        }?.filter { it != 0 } as? ArrayList<Int>

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
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PokemonTypesRow(
                pokemonTypes = pokemonTypes,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun PokemonTypesRow(
    pokemonTypes: ArrayList<Int>?,
    modifier: Modifier
) {
    LazyRow(
        modifier = modifier
    ) {
        itemsIndexed(
            items = pokemonTypes?.toList() ?: emptyList(),
            key = { id, _ -> id }
        ) { id, iconId ->
            Image(
                painter = painterResource(iconId),
                contentDescription = null
            )
            if (pokemonTypes?.size?.compareTo(1) == 1 && id < 1) {
                Spacer(
                    modifier = Modifier
                        .padding(
                            horizontal = dimensionResource(
                                R.dimen.padding_small
                            )
                        )
                )
            }
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun PokemonScreenPreview() {
    val pokemon: ArrayList<Pokemon> = ArrayList()
    for (i in 0..9) {
        pokemon.add(
            Pokemon(
                id = i,
                names = arrayListOf(
                    Pair(Language.SPANISH.tag, "Bulbasaur")
                ),
                sprites = arrayListOf("", ""),
                types = arrayListOf(PokemonType.GRASS, PokemonType.POISON),
                pokemonSpecies = arrayListOf(
                    Pair(Language.SPANISH.tag, "Pokemon semilla")
                )
            )
        )
    }
    PokemonGrid(
        context = LocalContext.current,
        pokemon = pokemon
    )
}