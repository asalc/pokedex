package es.shiro.pokedex.data.mappers

import es.shiro.pokedex.common.extensions.orDefault
import es.shiro.pokedex.data.model.local.ItemLocalDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import es.shiro.pokedex.data.model.remote.NameRemoteDto
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.model.Language

fun ItemRemoteDto.toDomain(): Item =
    Item(
        id = id.orDefault(),
        names = mapNamesList(names.orEmpty()),
        cost = cost.orDefault(),
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
    names: List<NameRemoteDto>
): List<Pair<String, String>> =
    names.map {
        Pair(
            it.language?.name.orEmpty(),
            it.name.orEmpty()
        )
    }