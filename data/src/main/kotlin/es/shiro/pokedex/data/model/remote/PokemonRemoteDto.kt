package es.shiro.pokedex.data.model.remote

import java.io.Serializable

class PokemonRemoteDto(
    val id: Int? = null,
    val name: String? = null,
    val sprites: PokemonSpriteRemoteDto? = null,
    val types: List<PokemonTypeRemoteDto>? = null,
    val speciesDetails: PokemonSpeciesRemoteDto? = null
): Serializable