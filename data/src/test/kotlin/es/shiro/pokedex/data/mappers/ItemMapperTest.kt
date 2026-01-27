package es.shiro.pokedex.data.mappers

import es.shiro.pokedex.data.mocks.emptyItem
import es.shiro.pokedex.data.mocks.emptyItemLocalDto
import es.shiro.pokedex.data.mocks.emptyItemRemoteDto
import es.shiro.pokedex.data.mocks.item
import es.shiro.pokedex.data.mocks.itemLocalDto
import es.shiro.pokedex.data.mocks.itemRemoteDto
import es.shiro.pokedex.data.mocks.nullItemRemoteDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ItemMapperTest {

    @Test
    fun `ItemMapper - ItemRemoteDto toDomain - Null data`() {
        // When
        val result = nullItemRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.sprite.isEmpty())
    }

    @Test
    fun `ItemMapper - ItemRemoteDto toDomain - Empty data`() {
        // When
        val result = emptyItemRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.sprite.isEmpty())
    }

    @Test
    fun `ItemMapper - ItemRemoteDto toDomain - Non-empty data`() {
        // When
        val result = itemRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isNotEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.sprite.isNotEmpty())
    }

    @Test
    fun `ItemMapper - ItemLocalDto toDomain - Empty data`() {
        // When
        val result = emptyItemLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.sprite.isEmpty())
    }

    @Test
    fun `ItemMapper - ItemLocalDto toDomain - Non-empty data`() {
        // When
        val result = itemLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isNotEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.sprite.isNotEmpty())
    }

    @Test
    fun `ItemMapper - Item toLocalDto - Empty data`() {
        // When
        val result = emptyItem.toLocalDto()

        // Then
        assertEquals(0, result.itemId)
        assertTrue(result.names.isEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.spriteUrl.isEmpty())
    }

    @Test
    fun `ItemMapper - Item toLocalDto - Non-empty data`() {
        // When
        val result = item.toLocalDto()

        // Then
        assertEquals(0, result.itemId)
        assertTrue(result.names.isNotEmpty())
        assertEquals(0, result.cost)
        assertTrue(result.spriteUrl.isNotEmpty())
    }
}