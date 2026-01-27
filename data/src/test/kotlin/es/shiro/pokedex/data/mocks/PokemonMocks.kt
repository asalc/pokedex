package es.shiro.pokedex.data.mocks

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.data.model.local.PokemonLocalDto
import es.shiro.pokedex.data.model.remote.NameRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpriteRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonTypeRemoteDto
import es.shiro.pokedex.domain.model.Language
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.model.PokemonType

internal val nullPokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto()
internal val emptyPokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto(
    id = 0,
    names = emptyList()
)
internal val pokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto(
    id = 0,
    names = listOf(NameRemoteDto())
)

internal val nullPokemonRemoteDto = PokemonRemoteDto(
    speciesDetails = nullPokemonSpeciesRemoteDto
)
internal val emptyPokemonRemoteDto = PokemonRemoteDto(
    id = null,
    name = String.EMPTY_STRING,
    sprites = null,
    types = emptyList(),
    speciesDetails = emptyPokemonSpeciesRemoteDto
)
internal val pokemonRemoteDto = PokemonRemoteDto(
    id = 0,
    name = "name",
    sprites = PokemonSpriteRemoteDto(
        backDefault = "backDefault",
        frontDefault = "frontDefault"
    ),
    types = listOf(PokemonTypeRemoteDto()),
    speciesDetails = pokemonSpeciesRemoteDto
)

internal val emptyPokemonLocalDto = PokemonLocalDto(
    pokemonId = 0,
    names = emptyList(),
    firstType = String.EMPTY_STRING,
    secondType = String.EMPTY_STRING,
    frontSpriteUrl = String.EMPTY_STRING,
    backSpriteUrl = String.EMPTY_STRING,
    species = PokemonSpecies()
)
internal val pokemonLocalDto = PokemonLocalDto(
    pokemonId = 0,
    names = listOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    firstType = "firstType",
    secondType = "secondType",
    frontSpriteUrl = "frontSpriteUrl",
    backSpriteUrl = "backSpriteUrl",
    species = PokemonSpecies(id = 0)
)

internal val emptyPokemon = Pokemon(
    id = 0,
    names = emptyList(),
    sprites = emptyList(),
    types = emptyList(),
    pokemonSpecies = PokemonSpecies()
)
internal val pokemon = Pokemon(
    id = 0,
    names = listOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    sprites = listOf("sprite"),
    types = listOf(PokemonType.FAIRY),
    pokemonSpecies = PokemonSpecies(
        id = 0,
        names = listOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING))
    )
)