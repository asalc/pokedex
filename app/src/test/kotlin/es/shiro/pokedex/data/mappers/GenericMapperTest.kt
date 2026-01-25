package es.shiro.pokedex.data.mappers

import es.shiro.data.mappers.toDomain
import es.shiro.pokedex.data.mocks.emptyGenericRemoteDto
import es.shiro.pokedex.data.mocks.genericRemoteDto
import es.shiro.pokedex.data.mocks.nullGenericRemoteDto
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class GenericMapperTest {

    @Test
    fun `GenericMapper - toDomain - Null data`() {
        // When
        val result = nullGenericRemoteDto.toDomain()

        // Then
        assertTrue(result.name.isEmpty())
        assertTrue(result.url.isEmpty())
    }

    @Test
    fun `GenericMapper - toDomain - Empty data`() {
        // When
        val result = emptyGenericRemoteDto.toDomain()

        // Then
        assertTrue(result.name.isEmpty())
        assertTrue(result.url.isEmpty())
    }

    @Test
    fun `GenericMapper - toDomain - Non-empty data`() {
        // When
        val result = genericRemoteDto.toDomain()

        // Then
        assertTrue(result.name.isNotEmpty())
        assertTrue(result.url.isNotEmpty())
    }
}