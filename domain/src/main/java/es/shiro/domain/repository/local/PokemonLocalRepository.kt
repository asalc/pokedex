package es.shiro.domain.repository.local

import es.shiro.domain.model.Pokemon
import es.shiro.domain.model.PokemonSpecies

interface PokemonLocalRepository {

    suspend fun insert(pokemon: Pokemon)

    suspend fun insertAll(vararg pokemon: Pokemon)

    suspend fun delete(pokemon: Pokemon)

    suspend fun deleteAll()

    suspend fun getAll(): Array<Pokemon>

    suspend fun getPokemonById(id: Int): Array<Pokemon>

    suspend fun getPokemonSpecies(id: Int): Array<PokemonSpecies?>

    suspend fun getCount(): Int
}