package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either

interface GetPokemonUseCase {
    suspend fun getPokemon(limit: Int): Either<Throwable, Int>
}