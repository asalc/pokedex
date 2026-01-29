package es.shiro.pokedex.presentation.screens.main.items

import arrow.core.raise.either
import es.shiro.pokedex.UiState
import es.shiro.pokedex.domain.use_cases.items.GetItemByIdUseCase
import es.shiro.pokedex.domain.use_cases.items.GetItemByIdUseCaseImpl
import es.shiro.pokedex.domain.use_cases.items.GetItemsUseCase
import es.shiro.pokedex.domain.use_cases.items.GetItemsUseCaseImpl
import es.shiro.pokedex.presentation.mocks.emptyItem
import es.shiro.pokedex.presentation.mocks.emptyItemCount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.atLeast
import org.mockito.kotlin.atMost
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class ItemsViewModelTest {

    private val getItemsUseCase: GetItemsUseCase = mock<GetItemsUseCaseImpl>()
    private val getItemByIdUseCase: GetItemByIdUseCase = mock<GetItemByIdUseCaseImpl>()

    private lateinit var viewModel: ItemsViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getItemsTotalCount - Error result`() =
        runTest {
            // Given
            whenever(getItemsUseCase.getItems(any()))
                .thenThrow(RuntimeException())

            try {
                viewModel = ItemsViewModel(
                    getItemsUseCase = getItemsUseCase,
                    getItemByIdUseCase = getItemByIdUseCase
                )
            } catch (_: Exception) {
                // Then
                verify(getItemsUseCase, times(1)).getItems(any())
                verifyNoInteractions(getItemByIdUseCase)
                assertEquals(UiState.Error, viewModel.uiState.value)
            }
        }

    @Test
    fun `getItemsTotalCount - Success result, getItems - Error result`() =
        runTest {
            // Given
            whenever(getItemsUseCase.getItems(any()))
                .thenReturn(either { emptyItemCount })
            whenever(getItemByIdUseCase.getItemById(any()))
                .thenThrow(RuntimeException())

            // When
            try {
                viewModel = ItemsViewModel(
                    getItemsUseCase = getItemsUseCase,
                    getItemByIdUseCase = getItemByIdUseCase
                )
            } catch (_: Exception) {
                // Then
                verify(getItemsUseCase, times(1)).getItems(any())
                verify(getItemByIdUseCase, atMost(1)).getItemById(any())
                assertEquals(UiState.Error, viewModel.uiState.value)
            }
        }

    @Test
    fun `getItemsTotalCount - Success result, getItems - Success result`() =
        runTest {
            // Given
            whenever(getItemsUseCase.getItems(any()))
                .thenReturn(either { emptyItemCount })
            whenever(getItemByIdUseCase.getItemById(any()))
                .thenReturn(either { emptyItem })

            // When
            viewModel = ItemsViewModel(
                getItemsUseCase = getItemsUseCase,
                getItemByIdUseCase = getItemByIdUseCase
            )

            // Then
            verify(getItemsUseCase, times(1)).getItems(any())
            assertEquals(UiState.ShowContent, viewModel.uiState.value)
        }
}