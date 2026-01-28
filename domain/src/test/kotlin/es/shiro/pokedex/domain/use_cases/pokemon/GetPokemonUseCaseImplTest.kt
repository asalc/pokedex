package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import arrow.core.raise.either
import es.shiro.pokedex.domain.mocks.emptyPager
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class GetPokemonUseCaseImplTest {

    private val localRepository: PokemonLocalRepository = mock()
    private val remoteRepository: PokemonRemoteRepository = mock()
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase = mock<GetPokemonByIdUseCaseImpl>()

    private lateinit var useCase: GetPokemonUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetPokemonUseCaseImpl(
            localRepository,
            remoteRepository,
            getPokemonByIdUseCase
        )
    }

    @Test
    fun `getPokemon - Success result - limit is greater than localRepository count`() =
        runBlocking {
            // Given
            val limit = 5
            whenever(remoteRepository.getPokemon(any(), any()))
                .thenReturn(either { emptyPager })
            whenever(localRepository.getCount()).thenReturn(0)

            // When
            val result = useCase.getPokemon(limit)

            // Then
            assertTrue(result.isRight())
            verify(getPokemonByIdUseCase, times(limit))
                .getPokemonById(any())

            return@runBlocking
        }

    @Test
    fun `getItems - Success result - localRepository count is greater than limit`() =
        runBlocking {
            // Given
            val limit = 0
            whenever(remoteRepository.getPokemon(any(), any()))
                .thenReturn(either { emptyPager })
            whenever(localRepository.getCount()).thenReturn(5)

            // When
            val result = useCase.getPokemon(limit)

            // Then
            assertTrue(result.isRight())
            verifyNoInteractions(getPokemonByIdUseCase)

            return@runBlocking
        }

    @Test
    fun `getItems - Error result`() =
        runBlocking {
            // Given
            whenever(remoteRepository.getPokemon(any(), any()))
                .thenThrow(RuntimeException())

            // When
            lateinit var result: Either<Throwable, Int>
            try {
                result = useCase.getPokemon(0)
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
                verifyNoInteractions(localRepository)
                verifyNoInteractions(getPokemonByIdUseCase)
            }

            return@runBlocking
        }
}