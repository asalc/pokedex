package es.shiro.pokedex.domain.model

class Item(
    override val id: Int,
    val names: List<Pair<String, String>>,
    val cost: Int,
    val sprite: String
) : GenericId(id)