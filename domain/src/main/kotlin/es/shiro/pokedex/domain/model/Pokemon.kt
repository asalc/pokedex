package es.shiro.pokedex.domain.model

class Pokemon(
    override val id: Int,
    var names: List<Pair<String, String>>,
    val sprites: List<String>,
    val types: List<PokemonType>,
    var pokemonSpecies: PokemonSpecies
) : GenericId(id)