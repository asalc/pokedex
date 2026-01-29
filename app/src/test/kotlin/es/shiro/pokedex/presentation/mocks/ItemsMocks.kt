package es.shiro.pokedex.presentation.mocks

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.domain.model.Item

internal val emptyItemCount = 0
internal val emptyItem = Item(
    id = 0,
    names = emptyList(),
    cost = 0,
    sprite = String.EMPTY_STRING
)