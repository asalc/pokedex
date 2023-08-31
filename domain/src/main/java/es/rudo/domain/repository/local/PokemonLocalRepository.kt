package es.rudo.domain.repository.local

import es.rudo.domain.model.Pokemon

interface PokemonLocalRepository {

    suspend fun insert(pokemon: Pokemon)

    suspend fun insertAll(vararg pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    suspend fun deleteAll()

    suspend fun getAll(): Array<Pokemon>

    suspend fun getPokemonById(id: Int): Array<Pokemon>

    suspend fun getCount(): Int
}