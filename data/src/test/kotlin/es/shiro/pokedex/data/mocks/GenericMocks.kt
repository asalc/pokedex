package es.shiro.pokedex.data.mocks

import es.shiro.pokedex.data.helpers.extensions.EMPTY_STRING
import es.shiro.pokedex.data.model.remote.GenericRemoteDto

internal val nullGenericRemoteDto = GenericRemoteDto()
internal val emptyGenericRemoteDto = GenericRemoteDto(
    name = String.EMPTY_STRING,
    url = String.EMPTY_STRING
)
internal val genericRemoteDto = GenericRemoteDto(
    name = "name",
    url = "url"
)