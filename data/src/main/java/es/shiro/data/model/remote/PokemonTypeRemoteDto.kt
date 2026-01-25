package es.shiro.data.model.remote

import java.io.Serializable

class PokemonTypeRemoteDto(
    val slot: Int? = null,
    val type: GenericRemoteDto? = null
): Serializable