package es.shiro.pokedex.data.repository.remote

import es.shiro.domain.repository.remote.ItemRemoteRepository
import es.shiro.domain.repository.remote.PokemonRemoteRepository
import es.shiro.pokedex.data.helpers.extensions.EMPTY_STRING
import es.shiro.pokedex.data.mocks.emptyGenericPager
import es.shiro.pokedex.data.mocks.emptyItemRemoteDto
import es.shiro.pokedex.data.mocks.emptyPokemonRemoteDto
import es.shiro.pokedex.data.mocks.emptyPokemonSpeciesRemoteDto
import es.shiro.pokedex.data.mocks.genericPager
import es.shiro.pokedex.data.mocks.itemRemoteDto
import es.shiro.pokedex.data.mocks.pokemonRemoteDto
import es.shiro.pokedex.data.mocks.pokemonSpeciesRemoteDto
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSourceImpl
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSourceImpl
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertNull
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import retrofit2.Response

class PokemonRemoteRepositoryImplTest {

    private val remoteSource: PokemonRemoteDataSource =
        mock<PokemonRemoteDataSourceImpl>()

    private lateinit var repository: PokemonRemoteRepository

    @BeforeEach
    fun setUp() {
        repository = PokemonRemoteRepositoryImpl(
            pokemonRemoteDataSource = remoteSource,
            context = mock()
        )
    }

    @Test
    fun `getPokemon - Empty success response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemon(any(), any()))
            .thenReturn(Response.success(emptyGenericPager))

        // When
        val result = repository.getPokemon()

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.count)
            assertNull(it.next)
            assertNull(it.previous)
            assertNull(it.results)
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getPokemon - Success response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemon(any(), any()))
            .thenReturn(Response.success(genericPager))

        // When
        val result = repository.getPokemon()

        // Then
        result.getOrNull()?.let {
            assertEquals(genericPager.count, it.count)
            assertFalse(it.next.isNullOrEmpty())
            assertFalse(it.previous.isNullOrEmpty())
            assertFalse(it.results.isNullOrEmpty())
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getPokemon - Error response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemon(any(), any()))
            .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

        try {
            // When
            repository.getPokemon()
        } catch (e: Exception) {
            // Then
            assertFalse(e.message.isNullOrEmpty())
        }
        return@runBlocking
    }

    @Test
    fun `getPokemonById - Empty success response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemonById(any()))
            .thenReturn(Response.success(emptyPokemonRemoteDto))

        // When
        val result = repository.getPokemonById(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertTrue(it.names?.isEmpty() == true)
            assertTrue(it.sprites?.firstOrNull()?.isEmpty() == true)
            assertTrue(it.sprites?.lastOrNull()?.isEmpty() == true)
            assertTrue(it.types?.isEmpty() == true)
            assertTrue(it.pokemonSpecies != null)
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getPokemonById - Success response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemonById(any()))
            .thenReturn(Response.success(pokemonRemoteDto))

        // When
        val result = repository.getPokemonById(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertFalse(it.names?.isEmpty() == true)
            assertFalse(it.sprites?.firstOrNull()?.isEmpty() == true)
            assertFalse(it.sprites?.lastOrNull()?.isEmpty() == true)
            assertFalse(it.types?.isEmpty() == true)
            assertTrue(it.pokemonSpecies != null)
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getPokemonById - Error response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemonById(any()))
            .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

        try {
            // When
            repository.getPokemonById(String.EMPTY_STRING)
        } catch (e: Exception) {
            // Then
            assertFalse(e.message.isNullOrEmpty())
        }
        return@runBlocking
    }

    @Test
    fun `getPokemonSpecies - Empty success response`() = runBlocking {
        // Given
        whenever(remoteSource.getPokemonSpecies(any()))
            .thenReturn(Response.success(emptyPokemonSpeciesRemoteDto))

        // When
        val result = repository.getPokemonSpecies(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertTrue(it.genera?.isEmpty() == true)
            assertTrue(it.names?.isEmpty() == true)
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getPokemonSpecies - Success response`() = runBlocking {
        // Given

        whenever(remoteSource.getPokemonSpecies(any()))
            .thenReturn(Response.success(pokemonSpeciesRemoteDto))
        // When
        val result = repository.getPokemonSpecies(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertFalse(it.genera?.isEmpty() == true)
            assertFalse(it.names?.isEmpty() == true)
            return@let
        }
        return@runBlocking
    }
}