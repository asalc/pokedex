package es.shiro.pokedex.data.mocks

import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.model.remote.GenericRemoteDto

internal val emptyGenericPager = Pager<GenericRemoteDto>()
internal val genericPager = Pager(
    count = 0,
    next = "next",
    previous = "previous",
    results = arrayListOf(genericRemoteDto)
)