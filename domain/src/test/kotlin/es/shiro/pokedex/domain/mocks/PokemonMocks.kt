package es.shiro.pokedex.domain.mocks

import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies

internal val emptyPokemon = Pokemon(
    id = 0,
    names = listOf(),
    sprites = listOf(),
    types = listOf(),
    pokemonSpecies = PokemonSpecies()
)

internal val emptyPokemonSpecies = PokemonSpecies()