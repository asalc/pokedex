package es.shiro.data.mappers

import es.shiro.data.model.local.ItemLocalDto
import es.shiro.data.model.remote.ItemRemoteDto
import es.shiro.data.model.remote.NameRemoteDto
import es.shiro.domain.model.Item
import es.shiro.domain.model.Language

fun ItemRemoteDto.toDomain(): Item =
    Item(
        id = id ?: 0,
        names = mapNamesList(names ?: ArrayList()),
        cost = cost ?: 0,
        sprite = sprites?.default.orEmpty()
    )

fun ItemLocalDto.toDomain(): Item =
    Item(
        id = itemId,
        names = names,
        cost = cost,
        sprite = spriteUrl
    )

fun Item.toLocalDto(): ItemLocalDto =
    ItemLocalDto(
        itemId = id,
        names = names.filter {
            it.first == Language.JAPANESE.tag ||
            it.first == Language.ENGLISH.tag  ||
            it.first == Language.SPANISH.tag
        } as ArrayList,
        cost = cost,
        spriteUrl = sprite
    )

private fun mapNamesList(
    names: ArrayList<NameRemoteDto>
): ArrayList<Pair<String, String>> =
    names.map {
        Pair(
            it.language?.name.orEmpty(),
            it.name.orEmpty()
        )
    } as ArrayList