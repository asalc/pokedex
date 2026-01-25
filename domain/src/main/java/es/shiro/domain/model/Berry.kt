package es.shiro.domain.model

class Berry(
    val id: Int,
    val itemId: Int,
    val name: ArrayList<Pair<String, String>>,
    val flavors: ArrayList<String>,
    val cost: Int,
    val sprite: String
)