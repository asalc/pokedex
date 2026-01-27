package es.shiro.pokedex.data.repository.local

import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.mappers.toLocalDto
import es.shiro.pokedex.data.repository.local.data_source.PokemonLocalDataSource
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository

class PokemonLocalRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource
): PokemonLocalRepository {

    override suspend fun insert(pokemon: Pokemon) {
        pokemonLocalDataSource.insert(
            pokemon.toLocalDto()
        )
    }

    override suspend fun getPokemonById(id: Int): List<Pokemon> {
        return pokemonLocalDataSource.getPokemonById(id).map { it.toDomain() }
    }

    override suspend fun getPokemonSpecies(id: Int): List<PokemonSpecies> {
        return pokemonLocalDataSource.getPokemonById(id).map { it.species }
    }

    override suspend fun getCount(): Int = pokemonLocalDataSource.getCount()
}