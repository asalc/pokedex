package es.rudo.data.mappers

import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.domain.model.Generic

fun GenericRemoteDto.toDomain(): Generic =
    Generic(
        name = name ?: "Unknown",
        url = url ?: ""
    )