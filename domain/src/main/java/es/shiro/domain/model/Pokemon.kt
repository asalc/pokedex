package es.shiro.domain.model

class Pokemon(
    override val id: Int,
    var names: ArrayList<Pair<String, String>>? = null,
    val sprites: ArrayList<String?>? = null,
    val types: ArrayList<PokemonType?>? = null,
    var pokemonSpecies: PokemonSpecies? = null
) : GenericId(id)