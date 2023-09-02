package es.rudo.data.mappers

import es.rudo.data.model.local.PokemonLocalDto
import es.rudo.data.model.remote.NameRemoteDto
import es.rudo.data.model.remote.PokemonRemoteDto
import es.rudo.data.model.remote.PokemonTypeRemoteDto
import es.rudo.domain.model.Language
import es.rudo.domain.model.Pokemon
import es.rudo.domain.model.PokemonType
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
        types = types?.map { mapType(it) } as ArrayList,
        pokemonSpecies = mapNamesList(
            speciesDetails?.genera?.map {
                NameRemoteDto(
                    name = it.name,
                    language = it.language
                )
            } as? ArrayList
        )
    )

fun PokemonLocalDto.toDomain(): Pokemon =
    Pokemon(
        id = pokemonId,
        names = name,
        sprites = arrayListOf(frontSpriteUrl, backSpriteUrl),
        types = arrayListOf(firstType, secondType).map { mapType(it) } as ArrayList,
        pokemonSpecies = species
    )

fun Pokemon.toLocalDto(): PokemonLocalDto =
    PokemonLocalDto(
        pokemonId = id,
        name = names,
        firstType = types?.first()?.label,
        secondType = types?.last()?.label,
        frontSpriteUrl = sprites?.first(),
        backSpriteUrl = sprites?.last(),
        species = pokemonSpecies
    )

private fun mapNamesList(
    names: ArrayList<NameRemoteDto>?
): ArrayList<Pair<String, String>>? =
    names?.map {
        Pair(
            it.language?.name ?: Language.ENGLISH.tag,
            it.name ?: "Unknown"
        )
    } as? ArrayList

private fun mapType(
    type: PokemonTypeRemoteDto
): PokemonType? =
    PokemonType.values().find { it.label == type.type?.name }

private fun mapType(
    type: String?
): PokemonType? = PokemonType.values().find { it.label == type }