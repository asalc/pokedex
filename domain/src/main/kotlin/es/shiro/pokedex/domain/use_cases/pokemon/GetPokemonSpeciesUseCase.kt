package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonSpeciesUseCase(
    private val pokemonLocalRepository: PokemonLocalRepository,
    private val pokemonRemoteRepository: PokemonRemoteRepository
) {
    suspend operator fun invoke(id: String): Either<Throwable, PokemonSpecies?> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                if (pokemonLocalRepository.getPokemonSpecies(id.toInt()).isEmpty()) {
                    pokemonRemoteRepository.getPokemonSpecies(id).fold(
                        ifLeft = { error -> throw error},
                        ifRight = { pokemonSpecies -> pokemonSpecies }
                    )
                } else {
                    pokemonLocalRepository.getPokemonSpecies(id.toInt()).first()
                }
            }
        }
}