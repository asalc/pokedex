package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import arrow.core.raise.either
import es.shiro.pokedex.domain.mocks.emptyPokemonSpecies
import es.shiro.pokedex.domain.model.PokemonSpecies
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

class GetPokemonSpeciesUseCaseImplTest {

    private val localRepository: PokemonLocalRepository = mock()
    private val remoteRepository: PokemonRemoteRepository = mock()

    private lateinit var useCase: GetPokemonSpeciesUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetPokemonSpeciesUseCaseImpl(
            localRepository,
            remoteRepository
        )
    }

    @Test
    fun `getPokemonSpecies - Found in localRepository`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonSpecies(any()))
                .thenReturn(emptyPokemonSpecies)

            // When
            val result = useCase.getPokemonSpecies(0.toString())

            // Then
            assertTrue(result.isRight())
            assertEquals(emptyPokemonSpecies.id, result.getOrNull()?.id)
            verifyNoInteractions(remoteRepository)

            return@runBlocking
        }

    @Test
    fun `getPokemonSpecies - Not found in localRepository, remote success result`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonSpecies(any()))
                .thenReturn(null)
            whenever(remoteRepository.getPokemonSpecies(any()))
                .thenReturn(either { emptyPokemonSpecies })

            // When
            val result = useCase.getPokemonSpecies(0.toString())

            // Then
            assertTrue(result.isRight())
            assertEquals(emptyPokemonSpecies.id, result.getOrNull()?.id)

            return@runBlocking
        }

    @Test
    fun `getPokemonSpecies - Not found in localRepository, remote error result`() =
        runBlocking {
            // Given
            whenever(localRepository.getPokemonSpecies(any()))
                .thenReturn(null)
            whenever(remoteRepository.getPokemonSpecies(any()))
                .thenThrow(RuntimeException())

            // When
            lateinit var result: Either<Throwable, PokemonSpecies>
            try {
                result = useCase.getPokemonSpecies(0.toString())
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
            }

            return@runBlocking
        }
}