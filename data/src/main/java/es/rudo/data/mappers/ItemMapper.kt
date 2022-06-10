package es.rudo.data.mappers

import es.rudo.data.model.local.ItemLocalDto
import es.rudo.data.model.remote.ItemRemoteDto
import es.rudo.data.model.remote.NameRemoteDto
import es.rudo.domain.model.Item
import es.rudo.domain.model.Language

fun ItemRemoteDto.toLocalDto(): ItemLocalDto =
    ItemLocalDto(
        itemId = id ?: 0,
        name = mapNamesList(
            names ?: ArrayList()
        ),
        cost = cost ?: 0,
        spriteUrl = sprites?.default ?: ""
    )

fun ItemRemoteDto.toDomain(): Item =
    Item(
        id = id ?: 0,
        name = mapNamesList(
            names ?: ArrayList()
        ),
        cost = cost ?: 0,
        sprite = sprites?.default ?: ""
    )

fun ItemLocalDto.toDomain(): Item =
    Item(
        id = itemId,
        name = name,
        cost = cost,
        sprite = spriteUrl
    )

fun Item.toLocalDto(): ItemLocalDto =
    ItemLocalDto(
        itemId = id,
        name = name.filter {
            it.first == Language.JAPANESE.label ||
            it.first == Language.ROMAJI.label   ||
            it.first == Language.ENGLISH.label  ||
            it.first == Language.SPANISH.label
        } as ArrayList,
        cost = cost,
        spriteUrl = sprite
    )

private fun mapNamesList(
    names: ArrayList<NameRemoteDto>
): ArrayList<Pair<String, String>> =
    names.map {
        Pair(
            it.language?.name ?: Language.ENGLISH.label,
            it.name ?: "Unknown"
        )
    } as ArrayList