package es.shiro.pokedex.data.model.remote

import java.io.Serializable

class GenericRemoteDto(
    val name: String? = null,
    val url: String? = null
): Serializable