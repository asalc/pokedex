package es.shiro.pokedex.data.mappers

import es.shiro.pokedex.data.mocks.emptyPokemon
import es.shiro.pokedex.data.mocks.emptyPokemonLocalDto
import es.shiro.pokedex.data.mocks.emptyPokemonRemoteDto
import es.shiro.pokedex.data.mocks.nullPokemonRemoteDto
import es.shiro.pokedex.data.mocks.pokemon
import es.shiro.pokedex.data.mocks.pokemonLocalDto
import es.shiro.pokedex.data.mocks.pokemonRemoteDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class PokemonMapperTest {

    @Test
    fun `PokemonRemoteDto - toDomain - Null data`() {
        // When
        val result = nullPokemonRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertTrue(result.sprites.all { it.isEmpty() })
        assertTrue(result.types.isEmpty())
        assertEquals(0, result.pokemonSpecies.id)
    }

    @Test
    fun `PokemonRemoteDto - toDomain - Empty data`() {
        // When
        val result = emptyPokemonRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertTrue(result.sprites.all { it.isEmpty() })
        assertTrue(result.types.isEmpty())
        assertEquals(0, result.pokemonSpecies.id)
    }

    @Test
    fun `PokemonRemoteDto - toDomain - Non-empty data`() {
        // When
        val result = pokemonRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertFalse(result.names.isEmpty())
        assertTrue(result.sprites.all { it.isNotEmpty() })
        assertFalse(result.types.isEmpty())
        assertEquals(0, result.pokemonSpecies.id)
    }

    @Test
    fun `PokemonLocalDto - toDomain - Empty data`() {
        // When
        val result = emptyPokemonLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names.isEmpty())
        assertTrue(result.sprites.all { it.isEmpty() })
        assertFalse(result.types.isEmpty())
        assertEquals(0, result.pokemonSpecies.id)
        assertTrue(result.pokemonSpecies.names.isEmpty())
    }

    @Test
    fun `PokemonLocalDto - toDomain - Non-empty data`() {
        // When
        val result = pokemonLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertFalse(result.names.isEmpty())
        assertTrue(result.sprites.all { it.isNotEmpty() })
        assertFalse(result.types.isEmpty())
        assertEquals(0, result.pokemonSpecies.id)
    }

    @Test
    fun `Pokemon - toLocalDto - Empty data`() {
        // When
        val result = emptyPokemon.toLocalDto()

        // Then
        assertEquals(0, result.pokemonId)
        assertTrue(result.names.isEmpty())
        assertTrue(result.firstType.isEmpty())
        assertTrue(result.secondType.isEmpty())
        assertTrue(result.frontSpriteUrl.isEmpty())
        assertTrue(result.backSpriteUrl.isEmpty())
        assertEquals(0, result.species.id)
    }

    @Test
    fun `Pokemon - toLocalDto - Non-empty data`() {
        // When
        val result = pokemon.toLocalDto()

        // Then
        assertEquals(0, result.pokemonId)
        assertFalse(result.names.isEmpty())
        assertFalse(result.firstType.isEmpty())
        assertFalse(result.secondType.isEmpty())
        assertFalse(result.frontSpriteUrl.isEmpty())
        assertFalse(result.backSpriteUrl.isEmpty())
        assertEquals(0, result.species.id)
    }
}