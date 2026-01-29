package es.shiro.pokedex.presentation.mocks

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies

internal val emptyCount = 0
internal val totalCount = 30
internal val emptyItem = Item(
    id = 0,
    names = emptyList(),
    cost = 0,
    sprite = String.EMPTY_STRING
)

internal val emptyPokemon = Pokemon(
    id = 0,
    names = emptyList(),
    sprites = emptyList(),
    types = emptyList(),
    pokemonSpecies = PokemonSpecies()
)