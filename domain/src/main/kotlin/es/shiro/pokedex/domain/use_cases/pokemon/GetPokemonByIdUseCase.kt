package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.model.Pokemon

interface GetPokemonByIdUseCase {
    suspend fun getPokemonById(id: Int): Either<Throwable, Pokemon>
}