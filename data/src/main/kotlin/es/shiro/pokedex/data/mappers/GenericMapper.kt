package es.shiro.pokedex.data.mappers

import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.domain.model.Generic

fun GenericRemoteDto.toDomain(): Generic =
    Generic(
        name = name.orEmpty(),
        url = url.orEmpty()
    )