package es.shiro.pokedex.data.mappers

import es.shiro.pokedex.common.extensions.orDefault
import es.shiro.pokedex.data.model.local.PokemonLocalDto
import es.shiro.pokedex.data.model.remote.NameRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonTypeRemoteDto
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.model.PokemonType

fun PokemonRemoteDto.toDomain(): Pokemon =
    Pokemon(
        id = id.orDefault(),
        names = mapNamesList(
            speciesDetails?.names.orEmpty()
        ),
        sprites = listOf(
            sprites?.frontDefault.orEmpty(),
            sprites?.backDefault.orEmpty()
        ),
        types = types?.map { mapType(it) }.orEmpty(),
        pokemonSpecies = speciesDetails?.toDomain() ?: PokemonSpecies()
    )

fun PokemonLocalDto.toDomain(): Pokemon =
    Pokemon(
        id = pokemonId,
        names = names,
        sprites = listOf(frontSpriteUrl, backSpriteUrl),
        types = listOf(firstType, secondType).map { mapType(it) },
        pokemonSpecies = species
    )

fun Pokemon.toLocalDto(): PokemonLocalDto =
    PokemonLocalDto(
        pokemonId = id,
        names = names,
        firstType = types.firstOrNull()?.label.orEmpty(),
        secondType = types.lastOrNull()?.label.orEmpty(),
        frontSpriteUrl = sprites.firstOrNull().orEmpty(),
        backSpriteUrl = sprites.lastOrNull().orEmpty(),
        species = pokemonSpecies
    )

fun PokemonSpeciesRemoteDto.toDomain(): PokemonSpecies =
    PokemonSpecies(
        id = id.orDefault(),
        names = mapNamesList(names.orEmpty()),
        genera = mapNamesList(
            genera?.map {
                NameRemoteDto(
                    name = it.name,
                    language = it.language
                )
            }.orEmpty()
        )
    )

private fun mapNamesList(
    names: List<NameRemoteDto>
): List<Pair<String, String>> =
    names.map {
        Pair(
            it.language?.name.orEmpty(),
            it.name.orEmpty()
        )
    }

private fun mapType(
    type: PokemonTypeRemoteDto
): PokemonType =
    PokemonType.entries.find { it.label == type.type?.name } ?: PokemonType.UNKNOWN

private fun mapType(
    type: String
): PokemonType = PokemonType.entries.find { it.label == type } ?: PokemonType.UNKNOWN