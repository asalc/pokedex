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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

    val itemsList = mutableStateListOf<Item>()
    val errorMessage = mutableStateOf(0)

    private var offset: Int = 1
    private val limit: Int = 20
    private var totalCount = 0

    init {
        getItemsTotalCount()
    }

    fun nextPage() {
        getItems()
    }

    fun previousPage() {
        offset -= limit
        getItems()
    }

    private fun getItemsTotalCount() {
        viewModelScope.launch {
            try {
                getItemsUseCase(limit).fold(
                    ifLeft = { error -> throw error },
                    ifRight = { itemCount ->
                        totalCount = itemCount
                        getItems()
                    }
                )
            } catch (e: Exception) {
                errorMessage.value = R.string.network_error
            }
        }
    }

    private fun getItems() {
        viewModelScope.launch {
            try {
                val firstIndex = offset
                for (i in firstIndex until firstIndex + limit) {
                    if (i <= totalCount) {
                        itemsList.add(
                            getItemByIdUseCase(i).fold(
                                ifLeft = { error -> throw error },
                                ifRight = { item ->
                                    if (offset < firstIndex + limit)
                                        offset++
                                    item
                                }
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = R.string.network_error
            }
        }
    }
}