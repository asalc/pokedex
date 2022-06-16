package es.rudo.pokedex.presentation.screens.main.items

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.domain.model.Item
import es.rudo.domain.use_cases.items.GetItemByIdUseCase
import es.rudo.domain.use_cases.items.GetItemsUseCase
import es.rudo.pokedex.App
import es.rudo.pokedex.R
import es.rudo.pokedex.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

    var itemsList = mutableStateListOf<Item>()
    val uiState = mutableStateOf<UiState>(UiState.Loading)

    var page = mutableStateOf(1)

    private val limit: Int = 20
    private var totalCount = 0

    init {
        getItemsTotalCount()
    }

    fun nextPage() {
        page.value += 1
        getItems()
    }

    fun previousPage() {
        page.value -= 1
        getItems()
    }

    fun isPreviousButtonVisible(): Boolean = page.value > 1
    fun isNextButtonVisible(): Boolean = page.value * limit + 1 < totalCount

    private fun getItemsTotalCount() {
        viewModelScope.launch {
            try {
                getItemsUseCase(limit).fold(
                    ifLeft = { error ->
                        uiState.value = UiState.Error(R.string.network_error)
                        throw error
                    },
                    ifRight = { itemCount ->
                        totalCount = itemCount
                        getItems()
                    }
                )
            } catch (e: Exception) {
                uiState.value = UiState.Error(R.string.network_error)
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            try {
                uiState.value = UiState.Loading
                itemsList.clear()
                val firstIndex = page.value * limit - limit + 1
                for (i in firstIndex until firstIndex + limit) {
                    if (i <= totalCount) {
                        itemsList.add(
                            getItemByIdUseCase(i).fold(
                                ifLeft = { error ->
                                    uiState.value = UiState.Error(R.string.network_error)
                                    throw error
                                },
                                ifRight = { item ->
                                    item
                                }
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