package es.shiro.pokedex.presentation.screens.main.pokemon

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.shiro.pokedex.UiState
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonByIdUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonUseCase
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
                getPokemonUseCase.getPokemon(limit).fold(
                    ifLeft = { error ->
                        uiState.value = UiState.Error
                        throw error
                    },
                    ifRight = { pokemonCount ->
                        totalCount.intValue = pokemonCount
                        getPokemon()
                    }
                )
            } catch (_: Exception) {
                uiState.value = UiState.Error
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
                            getPokemonByIdUseCase.getPokemonById(i).fold(
                                ifLeft = { error ->
                                    uiState.value = UiState.Error
                                    throw error
                                },
                                ifRight = { pokemon -> pokemon }
                            )
                        )
                    }
                }
                uiState.value = UiState.ShowContent
            } catch (_: Exception) {
                uiState.value = UiState.Error
            }
        }
    }
}