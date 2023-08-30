package es.rudo.pokedex.presentation.screens.main.pokemon

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import es.rudo.domain.model.Pokemon
import es.rudo.pokedex.UiState
import javax.inject.Inject

class PokemonViewModel @Inject constructor(

): ViewModel() {

    var pokemonList = mutableStateListOf<Pokemon>()
    val uiState = mutableStateOf<UiState>(UiState.Loading)

    var page = mutableStateOf(1)

    private val limit: Int = 20
    private var totalCount = 0

    init {
        //getPokemonTotalCount()
    }

    fun nextPage() {
        page.value += 1
        //getPokemon()
    }

    fun previousPage() {
        page.value -= 1
        //getPokemon()
    }

    fun isPreviousButtonVisible(): Boolean = page.value > 1
    fun isNextButtonVisible(): Boolean = page.value * limit + 1 < totalCount

}