package es.shiro.pokedex.data.repository.remote

import es.shiro.domain.repository.remote.ItemRemoteRepository
import es.shiro.pokedex.data.helpers.extensions.EMPTY_STRING
import es.shiro.pokedex.data.mocks.emptyGenericPager
import es.shiro.pokedex.data.mocks.emptyItemRemoteDto
import es.shiro.pokedex.data.mocks.genericPager
import es.shiro.pokedex.data.mocks.itemRemoteDto
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSourceImpl
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

class ItemRemoteRepositoryImplTest {

    private val remoteSource: ItemRemoteDataSource =
        mock<ItemRemoteDataSourceImpl>()

    private lateinit var repository: ItemRemoteRepository

    @BeforeEach
    fun setUp() {
        repository = ItemRemoteRepositoryImpl(
            itemRemoteDataSource = remoteSource,
            context = mock()
        )
    }

    @Test
    fun `getItems - Empty success response`() = runBlocking {
        // Given
        whenever(remoteSource.getItems(any(), any()))
            .thenReturn(Response.success(emptyGenericPager))

        // When
        val result = repository.getItems()

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
    fun `getItems - Success response`() = runBlocking {
        // Given
        whenever(remoteSource.getItems(any(), any()))
            .thenReturn(Response.success(genericPager))

        // When
        val result = repository.getItems()

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
    fun `getItems - Error response`() = runBlocking {
        // Given
        whenever(remoteSource.getItems(any(), any()))
            .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

        try {
            // When
            repository.getItems()
        } catch (e: Exception) {
            // Then
            assertFalse(e.message.isNullOrEmpty())
        }
        return@runBlocking
    }

    @Test
    fun `getItemById - Empty success response`() = runBlocking {
        // Given
        whenever(remoteSource.getItemById(any()))
            .thenReturn(Response.success(emptyItemRemoteDto))

        // When
        val result = repository.getItemById(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertTrue(it.names.isEmpty())
            assertEquals(0, it.cost)
            assertTrue(it.sprite.isEmpty())
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getItemById - Success response`() = runBlocking {
        // Given
        whenever(remoteSource.getItemById(any()))
            .thenReturn(Response.success(itemRemoteDto))

        // When
        val result = repository.getItemById(String.EMPTY_STRING)

        // Then
        result.getOrNull()?.let {
            assertEquals(0, it.id)
            assertTrue(it.names.isNotEmpty())
            assertEquals(0, it.cost)
            assertTrue(it.sprite.isNotEmpty())
            return@let
        }
        return@runBlocking
    }

    @Test
    fun `getItemById - Error response`() = runBlocking {
        // Given
        whenever(remoteSource.getItemById(any()))
            .thenReturn(Response.error(400, String.EMPTY_STRING.toResponseBody()))

        try {
            // When
            repository.getItemById(String.EMPTY_STRING)
        } catch (e: Exception) {
            // Then
            assertFalse(e.message.isNullOrEmpty())
        }
        return@runBlocking
    }
}