package es.rudo.data.model.remote

import java.io.Serializable

class PokemonKindRemoteDto(
    val genus: String? = null,
    val language: GenericRemoteDto? = null
): Serializable