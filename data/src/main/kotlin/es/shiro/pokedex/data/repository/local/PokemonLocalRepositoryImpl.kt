package es.shiro.pokedex.data.repository.local

import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.mappers.toLocalDto
import es.shiro.pokedex.data.repository.local.data_source.PokemonLocalDataSource
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository

class PokemonLocalRepositoryImpl(
    private val localDataSource: PokemonLocalDataSource
): PokemonLocalRepository {

    override suspend fun insert(pokemon: Pokemon) =
        localDataSource.insert(
            pokemon.toLocalDto()
        )

    override suspend fun getPokemonById(id: Int): Pokemon? =
        localDataSource.getPokemonById(id)?.toDomain()

    override suspend fun getPokemonSpecies(id: Int): PokemonSpecies? =
        localDataSource.getPokemonById(id)?.species

    override suspend fun getCount(): Int = localDataSource.getCount()
}