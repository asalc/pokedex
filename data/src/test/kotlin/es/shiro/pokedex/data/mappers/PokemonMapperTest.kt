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
        assertTrue(result.names?.isEmpty() == true)
        assertTrue(result.sprites?.all { it?.isEmpty() == true } == true)
        assertTrue(result.types?.isEmpty() == true)
        assertTrue(result.pokemonSpecies?.id == null)
    }

    @Test
    fun `PokemonRemoteDto - toDomain - Empty data`() {
        // When
        val result = emptyPokemonRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names?.isEmpty() == true)
        assertTrue(result.sprites?.all { it?.isEmpty() == true } == true)
        assertTrue(result.types?.isEmpty() == true)
        assertTrue(result.pokemonSpecies?.id == 0)
    }

    @Test
    fun `PokemonRemoteDto - toDomain - Non-empty data`() {
        // When
        val result = pokemonRemoteDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertFalse(result.names.isNullOrEmpty())
        assertTrue(result.sprites?.all { !it.isNullOrEmpty() } == true)
        assertFalse(result.types.isNullOrEmpty())
        assertTrue(result.pokemonSpecies?.id == 0)
    }

    @Test
    fun `PokemonLocalDto - toDomain - Empty data`() {
        // When
        val result = emptyPokemonLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertTrue(result.names?.isEmpty() == true)
        assertTrue(result.sprites?.all { it?.isEmpty() == true } == true)
        assertFalse(result.types.isNullOrEmpty())
        assertTrue(result.pokemonSpecies?.id == null)
    }

    @Test
    fun `PokemonLocalDto - toDomain - Non-empty data`() {
        // When
        val result = pokemonLocalDto.toDomain()

        // Then
        assertEquals(0, result.id)
        assertFalse(result.names.isNullOrEmpty())
        assertTrue(result.sprites?.all { !it.isNullOrEmpty() } == true)
        assertFalse(result.types.isNullOrEmpty())
        assertTrue(result.pokemonSpecies?.id == 0)
    }

    @Test
    fun `Pokemon - toLocalDto - Empty data`() {
        // When
        val result = emptyPokemon.toLocalDto()

        // Then
        assertEquals(0, result.pokemonId)
        assertTrue(result.names?.isEmpty() == true)
        assertTrue(result.firstType == null)
        assertTrue(result.secondType == null)
        assertTrue(result.frontSpriteUrl?.isEmpty() == true)
        assertTrue(result.backSpriteUrl?.isEmpty() == true)
        assertTrue(result.species?.id == null)
    }

    @Test
    fun `Pokemon - toLocalDto - Non-empty data`() {
        // When
        val result = pokemon.toLocalDto()

        // Then
        assertEquals(0, result.pokemonId)
        assertFalse(result.names.isNullOrEmpty())
        assertFalse(result.firstType?.isEmpty() == true)
        assertFalse(result.secondType?.isEmpty() == true)
        assertFalse(result.frontSpriteUrl?.isEmpty() == true)
        assertFalse(result.backSpriteUrl?.isEmpty() == true)
        assertTrue(result.species?.id == 0)
    }
}