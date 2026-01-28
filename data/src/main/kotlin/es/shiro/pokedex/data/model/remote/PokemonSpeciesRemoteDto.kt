package es.shiro.pokedex.data.model.remote

import java.io.Serializable

class PokemonSpeciesRemoteDto(
    val id: Int? = null,
    val names: List<NameRemoteDto>? = null,
    val genera: List<PokemonKindRemoteDto>? = null
): Serializable