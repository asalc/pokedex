package es.shiro.data.mappers

import es.shiro.data.model.local.PokemonLocalDto
import es.shiro.data.model.remote.NameRemoteDto
import es.shiro.data.model.remote.PokemonRemoteDto
import es.shiro.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.data.model.remote.PokemonTypeRemoteDto
import es.shiro.domain.model.Pokemon
import es.shiro.domain.model.PokemonSpecies
import es.shiro.domain.model.PokemonType
import kotlin.collections.ArrayList

fun PokemonRemoteDto.toDomain(): Pokemon =
    Pokemon(
        id = id ?: 0,
        names = mapNamesList(
            speciesDetails?.names ?: ArrayList()
        ),
        sprites = arrayListOf(
            sprites?.frontDefault.orEmpty(),
            sprites?.backDefault.orEmpty()
        ),
        types = (types?.map { mapType(it) } ?: arrayListOf()) as ArrayList,
        pokemonSpecies = speciesDetails?.toDomain()
    )

fun PokemonLocalDto.toDomain(): Pokemon =
    Pokemon(
        id = pokemonId,
        names = names,
        sprites = arrayListOf(frontSpriteUrl, backSpriteUrl),
        types = arrayListOf(firstType, secondType).map { mapType(it) } as ArrayList,
        pokemonSpecies = species
    )

fun Pokemon.toLocalDto(): PokemonLocalDto =
    PokemonLocalDto(
        pokemonId = id,
        names = names,
        firstType = types?.firstOrNull()?.label,
        secondType = types?.lastOrNull()?.label,
        frontSpriteUrl = sprites?.firstOrNull().orEmpty(),
        backSpriteUrl = sprites?.lastOrNull().orEmpty(),
        species = pokemonSpecies
    )

fun PokemonSpeciesRemoteDto.toDomain(): PokemonSpecies =
    PokemonSpecies(
        id = id,
        names = mapNamesList(names ?: ArrayList()),
        genera = mapNamesList(
            genera?.map {
                NameRemoteDto(
                    name = it.name,
                    language = it.language
                )
            } as? ArrayList
        )
    )

private fun mapNamesList(
    names: ArrayList<NameRemoteDto>?
): ArrayList<Pair<String, String>>? =
    names?.map {
        Pair(
            it.language?.name.orEmpty(),
            it.name.orEmpty()
        )
    } as? ArrayList

private fun mapType(
    type: PokemonTypeRemoteDto
): PokemonType? =
    PokemonType.entries.find { it.label == type.type?.name }

private fun mapType(
    type: String?
): PokemonType? = PokemonType.entries.find { it.label == type }