package es.shiro.data.model.remote

import java.io.Serializable

class PokemonRemoteDto(
    val id: Int? = null,
    val name: String? = null,
    val sprites: PokemonSpriteRemoteDto? = null,
    val types: ArrayList<PokemonTypeRemoteDto>? = null,
    val speciesDetails: PokemonSpeciesRemoteDto? = null
): Serializable