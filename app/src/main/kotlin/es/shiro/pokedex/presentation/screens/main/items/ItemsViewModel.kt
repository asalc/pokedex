package es.shiro.pokedex.presentation.screens.main.items

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import es.shiro.pokedex.UiState
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.use_cases.items.GetItemByIdUseCase
import es.shiro.pokedex.domain.use_cases.items.GetItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

    var itemsList = mutableStateListOf<Item>()
    val uiState = mutableStateOf<UiState>(UiState.Loading)
    var page = mutableIntStateOf(1)

    private val limit: Int = 20
    private var totalCount = 0

    init {
        getItemsTotalCount()
    }

    fun nextPage() {
        page.intValue += 1
        getItems()
    }

    fun previousPage() {
        page.intValue -= 1
        getItems()
    }

    fun isPreviousButtonVisible(): Boolean = page.intValue > 1
    fun isNextButtonVisible(): Boolean = page.intValue * limit + 1 < totalCount

    private fun getItemsTotalCount() {
        viewModelScope.launch {
            try {
                getItemsUseCase.getItems(limit).fold(
                    ifLeft = { error ->
                        uiState.value = UiState.Error
                        throw error
                    },
                    ifRight = { itemCount ->
                        totalCount = itemCount
                        getItems()
                    }
                )
            } catch (_: Exception) {
                uiState.value = UiState.Error
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            try {
                uiState.value = UiState.Loading
                itemsList.clear()
                val firstIndex = page.intValue * limit - limit + 1
                for (i in firstIndex until firstIndex + limit) {
                    if (i <= totalCount) {
                        itemsList.add(
                            getItemByIdUseCase.getItemById(i).fold(
                                ifLeft = { error ->
                                    uiState.value = UiState.Error
                                    throw error
                                },
                                ifRight = { item -> item }
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