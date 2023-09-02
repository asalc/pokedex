package es.rudo.pokedex.presentation.screens.main.pokemon

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.domain.model.Pokemon
import es.rudo.domain.use_cases.pokemon.GetPokemonByIdUseCase
import es.rudo.domain.use_cases.pokemon.GetPokemonUseCase
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
): ViewModel() {

    var pokemonList = mutableStateListOf<Pokemon>()
    val uiState = mutableStateOf<UiState>(UiState.Loading)

    var page = mutableIntStateOf(1)

    private val limit: Int = 20
    var totalCount = mutableIntStateOf(0)

    init {
        getPokemonTotalCount()
    }

    fun nextPage() {
        page.intValue += 1
        getPokemon()
    }

    fun previousPage() {
        page.intValue -= 1
        getPokemon()
    }

    fun isPreviousButtonVisible(): Boolean = page.intValue > 1
    fun isNextButtonVisible(): Boolean = page.intValue * limit + 1 < totalCount.intValue

    private fun getPokemonTotalCount() {
        viewModelScope.launch {
            try {
                getPokemonUseCase(limit).fold(
                    ifLeft = { error ->
                        uiState.value = UiState.Error(R.string.network_error)
                        throw error
                    },
                    ifRight = { pokemonCount ->
                        totalCount.intValue = pokemonCount
                        getPokemon()
                    }
                )
            } catch (e: Exception) {
                uiState.value = UiState.Error(R.string.network_error)
            }
        }
    }

    private fun getPokemon() {
        viewModelScope.launch {
            try {
                uiState.value = UiState.Loading
                pokemonList.clear()
                val firstIndex = page.intValue * limit - limit + 1
                for (i in firstIndex until firstIndex + limit) {
                    if (i <= totalCount.intValue) {
                        pokemonList.add(
                            getPokemonByIdUseCase(i).fold(
                                ifLeft = { error ->
                                    uiState.value = UiState.Error(R.string.network_error)
                                    throw error
                                },
                                ifRight = { pokemon -> pokemon }
                            )
                        )
                    }
                }
                uiState.value = UiState.ShowContent
            } catch (e: Exception) {
                uiState.value = UiState.Error(R.string.network_error)
            }
        }
    }
}