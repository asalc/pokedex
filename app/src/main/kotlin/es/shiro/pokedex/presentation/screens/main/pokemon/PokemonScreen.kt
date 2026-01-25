package es.shiro.pokedex.presentation.screens.main.pokemon

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
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import es.shiro.domain.model.Language
import es.shiro.domain.model.Pokemon
import es.shiro.domain.model.PokemonSpecies
import es.shiro.domain.model.PokemonType
import es.shiro.pokedex.R
import es.shiro.pokedex.UiState
import es.shiro.pokedex.helpers.extensions.findLanguageEntry
import es.shiro.pokedex.presentation.components.ErrorPopUp
import es.shiro.pokedex.presentation.components.PageButtons
import es.shiro.pokedex.presentation.components.PokedexGrid
import es.shiro.pokedex.presentation.components.ProgressLoader
import es.shiro.pokedex.presentation.theme.ColorRed

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
            pokemonTotalCount = viewModel.totalCount.intValue,
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
    pokemonTotalCount: Int,
    modifier: Modifier = Modifier
) {
    PokedexGrid(
        items = pokemon,
        modifier = modifier
    ) { pokemon ->
        PokemonCard(
            context = context,
            pokemon = pokemon,
            pokemonTotalCount = pokemonTotalCount
        )
    }
}

@Composable
fun PokemonCard(
    context: Context,
    pokemon: Pokemon,
    pokemonTotalCount: Int
) {
    val pokemonName: String =
        pokemon.names.findLanguageEntry(context)

    val pokemonSpecies: String =
        pokemon.pokemonSpecies?.genera.findLanguageEntry(context)

    val pokemonTypes: ArrayList<Int>? =
        pokemon.types?.distinct()?.map { type ->
            PokemonType.entries.find {
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
                    start = dimensionResource(R.dimen.padding_small),
                    top = dimensionResource(R.dimen.padding_small),
                    end = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_regular)
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            PokemonSprites(
                pokemonSprites = pokemon.sprites,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            PokemonNameTag(
                id = pokemon.id,
                name = pokemonName,
                pokemonTotalCount = pokemonTotalCount
            )
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.padding_small)
                )
            )
            Text(
                text = pokemonSpecies,
                textAlign = TextAlign.Center
            )
            Spacer(
                modifier = Modifier.height(
                    dimensionResource(R.dimen.padding_small)
                )
            )
            PokemonTypesRow(
                pokemonTypes = pokemonTypes,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun PokemonSprites(
    pokemonSprites: ArrayList<String?>?,
    modifier: Modifier
) {
    AsyncImage(
        model = pokemonSprites?.first(),
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .defaultMinSize(minHeight = 125.dp)
    )
}

@Composable
fun PokemonNameTag(
    id: Int,
    name: String,
    pokemonTotalCount: Int
) {
    val numberOfZeros = pokemonTotalCount.toString().length - id.toString().length
    Text(
        text = "#" + "0".repeat(numberOfZeros) + "$id\n" + name,
        textAlign = TextAlign.Center
    )
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
    val pokemon: ArrayList<Pokemon> = arrayListOf()
    for (i in 0..9) {
        pokemon.add(
            Pokemon(
                id = i,
                names = arrayListOf(
                    Pair(Language.SPANISH.tag, "Bulbasaur")
                ),
                sprites = arrayListOf("", ""),
                types = arrayListOf(PokemonType.GRASS, PokemonType.POISON),
                pokemonSpecies = PokemonSpecies(
                    names = arrayListOf(
                        Pair(Language.SPANISH.tag, "Bulbasaur")
                    ),
                    genera = arrayListOf(
                        Pair(Language.SPANISH.tag, "Pokemon semilla")
                    )
                )
            )
        )
    }
    PokemonGrid(
        context = LocalContext.current,
        pokemon = pokemon,
        pokemonTotalCount = 1000
    )
}