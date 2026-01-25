package es.shiro.domain.model

class Item(
    override val id: Int,
    val names: ArrayList<Pair<String, String>>,
    val cost: Int,
    val sprite: String
) : GenericId(id)