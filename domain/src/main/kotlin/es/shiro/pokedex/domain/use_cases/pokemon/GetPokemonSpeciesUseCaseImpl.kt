package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonSpeciesUseCaseImpl @Inject constructor(
    private val localRepository: PokemonLocalRepository,
    private val remoteRepository: PokemonRemoteRepository
) : GetPokemonSpeciesUseCase {

    override suspend fun getPokemonSpecies(id: String): Either<Throwable, PokemonSpecies> =
        withContext(Dispatchers.IO) {
            Either.catch {
                localRepository.getPokemonSpecies(id.toInt()) ?:
                    remoteRepository.getPokemonSpecies(id).fold(
                        ifLeft = { error -> throw error},
                        ifRight = { pokemonSpecies -> pokemonSpecies }
                    )
            }
        }
}