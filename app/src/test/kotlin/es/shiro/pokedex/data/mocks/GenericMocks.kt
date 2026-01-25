package es.shiro.pokedex.data.mocks

import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.helpers.extensions.EMPTY_STRING

internal val nullGenericRemoteDto = GenericRemoteDto()
internal val emptyGenericRemoteDto = GenericRemoteDto(
    name = String.EMPTY_STRING,
    url = String.EMPTY_STRING
)
internal val genericRemoteDto = GenericRemoteDto(
    name = "name",
    url = "url"
)