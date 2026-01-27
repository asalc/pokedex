package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.pokedex.data.api.Api
import es.shiro.pokedex.data.helpers.extensions.EMPTY_STRING
import es.shiro.pokedex.data.mocks.emptyGenericPager
import es.shiro.pokedex.data.mocks.emptyPokemonRemoteDto
import es.shiro.pokedex.data.mocks.emptyPokemonSpeciesRemoteDto
import es.shiro.pokedex.data.mocks.genericPager
import es.shiro.pokedex.data.mocks.pokemonRemoteDto
import es.shiro.pokedex.data.mocks.pokemonSpeciesRemoteDto
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

class PokemonRemoteDataSourceImplTest {

    private val api: Api = mock()

    private lateinit var remoteSource: PokemonRemoteDataSource

    @BeforeEach
    fun setUp() {
        remoteSource = PokemonRemoteDataSourceImpl(api)
    }

    @Test
    fun `getPokemon - Empty success result`() {
        runBlocking {
            // Given
            whenever(api.getPokemon(any(), any()))
                .thenReturn(Response.success(emptyGenericPager))

            // Whenever
            val result = remoteSource.getPokemon()

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(0, it.count)
                assertNull(it.next)
                assertNull(it.previous)
                assertNull(it.results)
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getPokemon - Success result`() {
        runBlocking {
            // Given
            whenever(api.getPokemon(any(), any()))
                .thenReturn(Response.success(genericPager))

            // Whenever
            val result = remoteSource.getPokemon()

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(genericPager.count, it.count)
                assertFalse(it.next.isNullOrEmpty())
                assertFalse(it.previous.isNullOrEmpty())
                assertFalse(it.results.isNullOrEmpty())
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getPokemon - Error result`() {
        runBlocking {
            // Given
            whenever(api.getPokemon(any(), any()))
                .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

            // Whenever
            val result = remoteSource.getPokemon()

            // Then
            assertFalse(result.isSuccessful)
            return@runBlocking
        }
    }

    @Test
    fun `getPokemonById - Empty success result`() {
        runBlocking {
            // Given
            whenever(api.getPokemonById(any()))
                .thenReturn(Response.success(emptyPokemonRemoteDto))

            // Whenever
            val result = remoteSource.getPokemonById(String.EMPTY_STRING)

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertNull(it.id)
                assertTrue(it.name?.isEmpty() == true)
                assertNull(it.sprites)
                assertTrue(it.types?.isEmpty() == true)
                assertEquals(emptyPokemonSpeciesRemoteDto.id, it.speciesDetails?.id)
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getPokemonById - Success result`() {
        runBlocking {
            // Given
            whenever(api.getPokemonById(any()))
                .thenReturn(Response.success(pokemonRemoteDto))

            // Whenever
            val result = remoteSource.getPokemonById(String.EMPTY_STRING)

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(0, it.id)
                assertEquals(pokemonRemoteDto.name, it.name)
                assertEquals(pokemonRemoteDto.sprites?.frontDefault, it.sprites?.frontDefault)
                assertEquals(pokemonRemoteDto.sprites?.backDefault, it.sprites?.backDefault)
                assertFalse(it.types.isNullOrEmpty())
                assertEquals(pokemonSpeciesRemoteDto.id, it.speciesDetails?.id)
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getPokemonById - Error result`() {
        runBlocking {
            // Given
            whenever(api.getPokemonById(any()))
                .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

            // Whenever
            val result = remoteSource.getPokemonById(String.EMPTY_STRING)

            // Then
            assertFalse(result.isSuccessful)
            return@runBlocking
        }
    }
}