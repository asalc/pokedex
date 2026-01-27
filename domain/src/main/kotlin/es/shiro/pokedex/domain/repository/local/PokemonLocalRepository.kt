package es.shiro.pokedex.domain.repository.local

import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies

interface PokemonLocalRepository {

    suspend fun insert(pokemon: Pokemon)

    suspend fun getPokemonById(id: Int): List<Pokemon>

    suspend fun getPokemonSpecies(id: Int): List<PokemonSpecies>

    suspend fun getCount(): Int
}