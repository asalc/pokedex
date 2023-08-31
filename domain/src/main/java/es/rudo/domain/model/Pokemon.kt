package es.rudo.domain.model

class Pokemon(
    val id: Int,
    val names: ArrayList<Pair<String, String>>,
    val sprites: ArrayList<String?>? = null,
    val types: ArrayList<PokemonType?>? = null,
    val pokemonSpecies: ArrayList<Pair<String, String>>? = null
)