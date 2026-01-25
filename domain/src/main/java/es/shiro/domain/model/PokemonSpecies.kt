package es.shiro.domain.model

import java.io.Serializable

class PokemonSpecies(
    val id: Int? = null,
    val names: ArrayList<Pair<String, String>>? = null,
    val genera: ArrayList<Pair<String, String>>? = null
): Serializable