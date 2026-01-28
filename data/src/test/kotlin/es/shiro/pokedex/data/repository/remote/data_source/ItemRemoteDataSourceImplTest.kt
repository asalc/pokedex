package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.data.api.PokedexApi
import es.shiro.pokedex.data.mocks.emptyGenericPager
import es.shiro.pokedex.data.mocks.emptyItemRemoteDto
import es.shiro.pokedex.data.mocks.genericPager
import es.shiro.pokedex.data.mocks.itemRemoteDto
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

class ItemRemoteDataSourceImplTest {

    private val api: PokedexApi = mock()

    private lateinit var remoteSource: ItemRemoteDataSource

    @BeforeEach
    fun setUp() {
        remoteSource = ItemRemoteDataSourceImpl(api)
    }

    @Test
    fun `getItems - Empty success result`() {
        runBlocking {
            // Given
            whenever(api.getItems(any(), any()))
                .thenReturn(Response.success(emptyGenericPager))

            // Whenever
            val result = remoteSource.getItems()

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(0, it.count)
                assertTrue(it.next.isEmpty())
                assertTrue(it.previous.isEmpty())
                assertTrue(it.results.isEmpty())
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getItems - Success result`() {
        runBlocking {
            // Given
            whenever(api.getItems(any(), any()))
                .thenReturn(Response.success(genericPager))

            // Whenever
            val result = remoteSource.getItems()

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(genericPager.count, it.count)
                assertFalse(it.next.isEmpty())
                assertFalse(it.previous.isEmpty())
                assertFalse(it.results.isEmpty())
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getItems - Error result`() {
        runBlocking {
            // Given
            whenever(api.getItems(any(), any()))
                .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

            // Whenever
            val result = remoteSource.getItems()

            // Then
            assertFalse(result.isSuccessful)
            return@runBlocking
        }
    }

    @Test
    fun `getItemById - Empty success result`() {
        runBlocking {
            // Given
            whenever(api.getItemById(any()))
                .thenReturn(Response.success(emptyItemRemoteDto))

            // Whenever
            val result = remoteSource.getItemById(String.EMPTY_STRING)

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertNull(it.id)
                assertTrue(it.name?.isEmpty() == true)
                assertNull(it.cost)
                assertTrue(it.names?.isEmpty() == true)
                assertNull(it.sprites)
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getItemById - Success result`() {
        runBlocking {
            // Given
            whenever(api.getItemById(any()))
                .thenReturn(Response.success(itemRemoteDto))

            // Whenever
            val result = remoteSource.getItemById(String.EMPTY_STRING)

            // Then
            assertTrue(result.isSuccessful)
            result.body()?.let {
                assertEquals(0, it.id)
                assertFalse(it.name.isNullOrEmpty())
                assertEquals(0, it.cost)
                assertFalse(it.names.isNullOrEmpty())
                assertEquals(itemRemoteDto.sprites?.default, it.sprites?.default)
                return@let
            }
            return@runBlocking
        }
    }

    @Test
    fun `getItemById - Error result`() {
        runBlocking {
            // Given
            whenever(api.getItemById(any()))
                .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

            // Whenever
            val result = remoteSource.getItemById(String.EMPTY_STRING)

            // Then
            assertFalse(result.isSuccessful)
            return@runBlocking
        }
    }
}