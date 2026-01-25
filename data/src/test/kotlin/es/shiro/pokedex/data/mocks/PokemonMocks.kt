package es.shiro.pokedex.data.mocks

import es.shiro.data.helpers.extensions.EMPTY_STRING
import es.shiro.data.model.local.PokemonLocalDto
import es.shiro.data.model.remote.NameRemoteDto
import es.shiro.data.model.remote.PokemonKindRemoteDto
import es.shiro.data.model.remote.PokemonRemoteDto
import es.shiro.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.data.model.remote.PokemonSpriteRemoteDto
import es.shiro.data.model.remote.PokemonTypeRemoteDto
import es.shiro.domain.model.Language
import es.shiro.domain.model.Pokemon
import es.shiro.domain.model.PokemonSpecies
import es.shiro.domain.model.PokemonType

internal val nullPokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto()
internal val emptyPokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto(
    id = 0,
    genera = arrayListOf(),
    names = arrayListOf()
)
internal val pokemonSpeciesRemoteDto = PokemonSpeciesRemoteDto(
    id = 0,
    genera = arrayListOf(PokemonKindRemoteDto()),
    names = arrayListOf(NameRemoteDto())
)

internal val nullPokemonRemoteDto = PokemonRemoteDto(
    speciesDetails = nullPokemonSpeciesRemoteDto
)
internal val emptyPokemonRemoteDto = PokemonRemoteDto(
    id = null,
    name = String.EMPTY_STRING,
    sprites = null,
    types = arrayListOf(),
    speciesDetails = emptyPokemonSpeciesRemoteDto
)
internal val pokemonRemoteDto = PokemonRemoteDto(
    id = 0,
    name = "name",
    sprites = PokemonSpriteRemoteDto(
        backDefault = "backDefault",
        frontDefault = "frontDefault"
    ),
    types = arrayListOf(PokemonTypeRemoteDto()),
    speciesDetails = pokemonSpeciesRemoteDto
)

internal val emptyPokemonLocalDto = PokemonLocalDto(
    pokemonId = 0,
    names = arrayListOf(),
    firstType = String.EMPTY_STRING,
    secondType = String.EMPTY_STRING,
    frontSpriteUrl = String.EMPTY_STRING,
    backSpriteUrl = String.EMPTY_STRING,
    species = null
)
internal val pokemonLocalDto = PokemonLocalDto(
    pokemonId = 0,
    names = arrayListOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    firstType = "firstType",
    secondType = "secondType",
    frontSpriteUrl = "frontSpriteUrl",
    backSpriteUrl = "backSpriteUrl",
    species = PokemonSpecies(id = 0)
)

internal val emptyPokemon = Pokemon(
    id = 0,
    names = arrayListOf(),
    sprites = arrayListOf(),
    types = arrayListOf(),
    pokemonSpecies = null
)
internal val pokemon = Pokemon(
    id = 0,
    names = arrayListOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    sprites = arrayListOf("sprite"),
    types = arrayListOf(PokemonType.FAIRY),
    pokemonSpecies = PokemonSpecies(
        id = 0,
        names = arrayListOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
        genera = arrayListOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING))
    )
)