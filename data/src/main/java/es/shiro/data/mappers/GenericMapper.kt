package es.shiro.data.mappers

import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.domain.model.Generic

fun GenericRemoteDto.toDomain(): Generic =
    Generic(
        name = name.orEmpty(),
        url = url.orEmpty()
    )