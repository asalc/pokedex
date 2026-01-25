package es.shiro.data.model.remote

import java.io.Serializable

class ItemRemoteDto(
    val id: Int? = null,
    val name: String? = null,
    val cost: Int? = null,
    val names: ArrayList<NameRemoteDto>? = null,
    val sprites: ItemSpriteRemoteDto? = null
): Serializable