package es.rudo.domain.model

class Pokemon(
    val id: Int,
    val name: ArrayList<Pair<String, String>>,
    val abilities: ArrayList<String>,
    val sprites: ArrayList<Pair<String, String>>,
    val types: ArrayList<String>,
    val evolvesFrom: Int
)