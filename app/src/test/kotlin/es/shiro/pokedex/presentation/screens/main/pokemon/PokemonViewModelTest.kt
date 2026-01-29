package es.shiro.pokedex.presentation.screens.main.pokemon

import arrow.core.raise.either
import es.shiro.pokedex.UiState
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonByIdUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonByIdUseCaseImpl
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonUseCaseImpl
import es.shiro.pokedex.presentation.mocks.emptyCount
import es.shiro.pokedex.presentation.mocks.emptyPokemon
import es.shiro.pokedex.presentation.mocks.totalCount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.atMost
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonViewModelTest {

    private val getPokemonUseCase: GetPokemonUseCase = mock<GetPokemonUseCaseImpl>()
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase = mock<GetPokemonByIdUseCaseImpl>()

    private lateinit var viewModel: PokemonViewModel

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
    fun `nextPage increases the page value as expected`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { emptyCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })

            // When
            initializeViewModel()
            val initialPage = viewModel.page.intValue
            viewModel.nextPage()

            // Then
            assertEquals(initialPage + 1, viewModel.page.intValue)
        }

    @Test
    fun `previousPage decreases the page value as expected`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { emptyCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })

            // When
            initializeViewModel()
            val initialPage = viewModel.page.intValue
            viewModel.previousPage()

            // Then
            assertEquals(initialPage - 1, viewModel.page.intValue)
        }

    @Test
    fun `isPreviousButtonVisible returns expected value`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { emptyCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })

            // When
            initializeViewModel()
            val initialVisibility = viewModel.isPreviousButtonVisible()
            viewModel.nextPage()

            // Then
            assertFalse(initialVisibility)
            assertTrue(viewModel.isPreviousButtonVisible())
        }

    @Test
    fun `isNextButtonVisible returns expected value`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { totalCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })

            // When
            initializeViewModel()
            val initialVisibility = viewModel.isNextButtonVisible()
            viewModel.nextPage()

            // Then
            assertTrue(initialVisibility)
            assertFalse(viewModel.isNextButtonVisible())
        }

    @Test
    fun `getPokemonTotalCount - Error result`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenThrow(RuntimeException())

            try {
                initializeViewModel()
            } catch (_: Exception) {
                // Then
                verify(getPokemonUseCase, times(1)).getPokemon(any())
                verifyNoInteractions(getPokemonByIdUseCase)
                assertEquals(UiState.Error, viewModel.uiState.value)
            }
        }

    @Test
    fun `getPokemonTotalCount - Success result, getPokemon - Error result`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { emptyCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenThrow(RuntimeException())

            // When
            try {
                initializeViewModel()
            } catch (_: Exception) {
                // Then
                verify(getPokemonUseCase, times(1)).getPokemon(any())
                verify(getPokemonByIdUseCase, atMost(1)).getPokemonById(any())
                assertEquals(UiState.Error, viewModel.uiState.value)
            }
        }

    @Test
    fun `getPokemonTotalCount - Success result, getPokemon - Success result`() =
        runTest {
            // Given
            whenever(getPokemonUseCase.getPokemon(any()))
                .thenReturn(either { emptyCount })
            whenever(getPokemonByIdUseCase.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })

            // When
            initializeViewModel()

            // Then
            verify(getPokemonUseCase, times(1)).getPokemon(any())
            assertEquals(UiState.ShowContent, viewModel.uiState.value)
        }

    private fun initializeViewModel() {
        viewModel = PokemonViewModel(
            getPokemonUseCase = getPokemonUseCase,
            getPokemonByIdUseCase = getPokemonByIdUseCase
        )
    }
}