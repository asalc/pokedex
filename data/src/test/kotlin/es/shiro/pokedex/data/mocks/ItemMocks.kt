package es.shiro.pokedex.data.mocks

import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.data.model.local.ItemLocalDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import es.shiro.pokedex.data.model.remote.ItemSpriteRemoteDto
import es.shiro.pokedex.data.model.remote.NameRemoteDto
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.model.Language

internal val nullItemRemoteDto = ItemRemoteDto()
internal val emptyItemRemoteDto = ItemRemoteDto(
    id = null,
    name = String.EMPTY_STRING,
    cost = null,
    names = emptyList(),
    sprites = null
)
internal val itemRemoteDto = ItemRemoteDto(
    id = 0,
    name = "name",
    cost = 0,
    names = listOf(NameRemoteDto()),
    sprites = ItemSpriteRemoteDto("sprite")
)

internal val emptyItemLocalDto = ItemLocalDto(
    itemId = 0,
    names = emptyList(),
    cost = 0,
    spriteUrl = String.EMPTY_STRING
)
internal val itemLocalDto = ItemLocalDto(
    itemId = 0,
    names = listOf(Pair(String.EMPTY_STRING, String.EMPTY_STRING)),
    cost = 0,
    spriteUrl = "spriteUrl"
)

internal val emptyItem = Item(
    id = 0,
    names = emptyList(),
    cost = 0,
    sprite = String.EMPTY_STRING
)
internal val item = Item(
    id = 0,
    names = listOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    cost = 0,
    sprite = "sprite"
)