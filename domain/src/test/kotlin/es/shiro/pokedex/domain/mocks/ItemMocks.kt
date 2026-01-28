package es.shiro.pokedex.domain.mocks

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.domain.model.Item

internal val emptyItem = Item(
    id = 0,
    names = listOf(),
    cost = 0,
    sprite = String.EMPTY_STRING
)