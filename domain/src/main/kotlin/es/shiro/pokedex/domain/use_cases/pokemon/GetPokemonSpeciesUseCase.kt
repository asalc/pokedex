package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.model.PokemonSpecies

interface GetPokemonSpeciesUseCase {
    suspend fun getPokemonSpecies(id: String): Either<Throwable, PokemonSpecies>
}