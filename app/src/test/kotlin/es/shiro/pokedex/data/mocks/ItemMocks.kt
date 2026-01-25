package es.shiro.pokedex.data.mocks

import es.shiro.data.model.local.ItemLocalDto
import es.shiro.data.model.remote.ItemRemoteDto
import es.shiro.data.model.remote.ItemSpriteRemoteDto
import es.shiro.data.model.remote.NameRemoteDto
import es.shiro.domain.model.Item
import es.shiro.domain.model.Language
import es.shiro.pokedex.helpers.extensions.EMPTY_STRING

internal val nullItemRemoteDto = ItemRemoteDto()
internal val emptyItemRemoteDto = ItemRemoteDto(
    id = null,
    name = String.EMPTY_STRING,
    cost = null,
    names = arrayListOf(),
    sprites = null
)
internal val itemRemoteDto = ItemRemoteDto(
    id = 0,
    name = "name",
    cost = 0,
    names = arrayListOf(NameRemoteDto()),
    sprites = ItemSpriteRemoteDto("sprite")
)

internal val emptyItemLocalDto = ItemLocalDto(
    itemId = 0,
    names = arrayListOf(),
    cost = 0,
    spriteUrl = String.EMPTY_STRING
)
internal val itemLocalDto = ItemLocalDto(
    itemId = 0,
    names = arrayListOf(Pair(String.EMPTY_STRING, String.EMPTY_STRING)),
    cost = 0,
    spriteUrl = "spriteUrl"
)

internal val emptyItem = Item(
    id = 0,
    names = arrayListOf(),
    cost = 0,
    sprite = String.EMPTY_STRING
)
internal val item = Item(
    id = 0,
    names = arrayListOf(Pair(Language.JAPANESE.tag, String.EMPTY_STRING)),
    cost = 0,
    sprite = "sprite"
)