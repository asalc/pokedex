package es.rudo.domain.repository.remote

import arrow.core.Either
import es.rudo.domain.helpers.Pager
import es.rudo.domain.model.Generic
import es.rudo.domain.model.Pokemon
import es.rudo.domain.model.PokemonSpecies

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