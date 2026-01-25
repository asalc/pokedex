package es.shiro.domain.model

class Item(
    val id: Int,
    val name: ArrayList<Pair<String, String>>,
    val cost: Int,
    val sprite: String
)