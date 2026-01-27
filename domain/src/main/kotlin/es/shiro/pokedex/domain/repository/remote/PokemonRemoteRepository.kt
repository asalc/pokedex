package es.shiro.pokedex.domain.repository.remote

import arrow.core.Either
import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.domain.model.Generic
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies

interface PokemonRemoteRepository {

    suspend fun getPokemon(
        offset: Int = 0,
        limit: Int = 1
    ): Either<Throwable, Pager<Generic>>

    suspend fun getPokemonById(
        id: String
    ): Either<Throwable, Pokemon>

    suspend fun getPokemonSpecies(
        id: String
    ): Either<Throwable, PokemonSpecies>
}