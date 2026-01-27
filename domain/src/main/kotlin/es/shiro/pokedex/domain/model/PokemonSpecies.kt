package es.shiro.pokedex.domain.model

import java.io.Serializable

class PokemonSpecies(
    val id: Int = 0,
    val names: List<Pair<String, String>> = emptyList()
): Serializable