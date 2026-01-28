package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import arrow.core.raise.either
import es.shiro.pokedex.domain.mocks.emptyPokemon
import es.shiro.pokedex.domain.mocks.emptyPokemonSpecies
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class GetPokemonByIdUseCaseImplTest {

    private val localRepository: PokemonLocalRepository = mock()
    private val remoteRepository: PokemonRemoteRepository = mock()
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase =
        mock<GetPokemonSpeciesUseCaseImpl>()

    private lateinit var useCase: GetPokemonByIdUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetPokemonByIdUseCaseImpl(
            localRepository,
            remoteRepository,
            getPokemonSpeciesUseCase
        )
    }

    @Test
    fun `getPokemonById - Found in localRepository`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonById(any()))
                .thenReturn(emptyPokemon)

            // When
            val result = useCase.getPokemonById(0).getOrNull()

            // Then
            assertTrue(result != null)
            assertEquals(emptyPokemon.id, result?.id)
            verifyNoInteractions(remoteRepository)
            verifyNoInteractions(getPokemonSpeciesUseCase)

            return@runBlocking
        }

    @Test
    fun `getPokemonById - Not found in localRepository, getPokemonById success result`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonById(any()))
                .thenReturn(null)
            whenever(remoteRepository.getPokemonById(any()))
                .thenReturn(either { emptyPokemon })
            whenever(remoteRepository.getPokemonSpecies(any()))
                .thenThrow(RuntimeException())

            // When
            lateinit var result: Either<Throwable, Pokemon?>
            try {
                result = useCase.getPokemonById(0)
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
            }

            return@runBlocking
        }

    @Test
    fun `getPokemonById - Not found in localRepository, getPokemonSpecies success result`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonById(any()))
                .thenReturn(null)
            whenever(remoteRepository.getPokemonById(any()))
                .thenThrow(RuntimeException())
            whenever(remoteRepository.getPokemonSpecies(any()))
                .thenReturn(either { emptyPokemonSpecies })

            // When
            lateinit var result: Either<Throwable, Pokemon?>
            try {
                result = useCase.getPokemonById(0)
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
                verifyNoInteractions(getPokemonSpeciesUseCase)
            }

            return@runBlocking
        }
}