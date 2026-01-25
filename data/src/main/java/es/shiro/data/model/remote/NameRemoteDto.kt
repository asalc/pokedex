package es.shiro.data.model.remote

import java.io.Serializable

class NameRemoteDto(
    val language: GenericRemoteDto? = null,
    val name: String? = null
): Serializable