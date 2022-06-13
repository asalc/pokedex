package es.rudo.pokedex.presentation.screens.main.items

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.right
import dagger.hilt.android.lifecycle.HiltViewModel
import es.rudo.domain.model.Item
import es.rudo.domain.use_cases.items.GetItemByIdUseCase
import es.rudo.domain.use_cases.items.GetItemsUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val getItemsUseCase: GetItemsUseCase,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : ViewModel() {

    val itemsList = mutableStateListOf<Item>()
    val errorMessage = MutableLiveData("")

    private var offset: Int = 1
    private val limit: Int = 20

    fun getItems() {
        viewModelScope.launch {
            getItemsUseCase()
            try {
                for (i in offset until offset + limit) {
                    itemsList.add(
                        getItemByIdUseCase(i).fold(
                            ifLeft = { error -> throw error },
                            ifRight = { item -> item }
                        )
                    )
                }
                offset += limit + 1
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}