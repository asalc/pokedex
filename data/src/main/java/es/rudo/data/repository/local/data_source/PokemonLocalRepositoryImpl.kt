package es.rudo.data.repository.local.data_source

import es.rudo.data.mappers.toDomain
import es.rudo.data.mappers.toLocalDto
import es.rudo.domain.model.Pokemon
import es.rudo.domain.model.PokemonSpecies
import es.rudo.domain.repository.local.PokemonLocalRepository

class PokemonLocalRepositoryImpl(
    private val pokemonLocalDataSource: PokemonLocalDataSource
): PokemonLocalRepository {

    override suspend fun insert(pokemon: Pokemon) {
        pokemonLocalDataSource.insert(
            pokemon.toLocalDto()
        )
    }

    override suspend fun insertAll(vararg pokemon: Pokemon) {
        pokemonLocalDataSource.insertAll(
            *pokemon.map {
                it.toLocalDto()
            }.toTypedArray()
        )
    }

    override suspend fun delete(pokemon: Pokemon) {
        pokemonLocalDataSource.delete(
            pokemon.toLocalDto()
        )
    }

    override suspend fun deleteAll() {
        pokemonLocalDataSource.deleteAll()
    }

    override suspend fun getAll(): Array<Pokemon> {
        return pokemonLocalDataSource.getAll().map {
            it.toDomain()
        }.toTypedArray()
    }

    override suspend fun getPokemonById(id: Int): Array<Pokemon> {
        return pokemonLocalDataSource.getPokemonById(id)
            .map { it.toDomain() }
            .toTypedArray()
    }

    override suspend fun getPokemonSpecies(id: Int): Array<PokemonSpecies?> {
        return pokemonLocalDataSource.getPokemonById(id).map { it.species }.toTypedArray()
    }

    override suspend fun getCount(): Int = pokemonLocalDataSource.getCount()
}