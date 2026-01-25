package es.shiro.data.model.remote

import java.io.Serializable

class PokemonSpeciesRemoteDto(
    val id: Int? = null,
    val genera: ArrayList<PokemonKindRemoteDto>? = null,
    val names: ArrayList<NameRemoteDto>? = null,
): Serializable