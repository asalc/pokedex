package es.rudo.domain.use_cases.pokemon

import arrow.core.Either
import es.rudo.domain.model.Pokemon
import es.rudo.domain.repository.local.PokemonLocalRepository
import es.rudo.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonByIdUseCase(
    private val pokemonLocalRepository: PokemonLocalRepository,
    private val pokemonRemoteRepository: PokemonRemoteRepository
) {
    suspend operator fun invoke(id: Int): Either<Throwable, Pokemon> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                if (pokemonLocalRepository.getPokemonById(id).isEmpty()) {
                    pokemonRemoteRepository.getPokemonById(id.toString()).fold(
                        ifLeft = { error -> throw error },
                        ifRight = { pokemon ->
                            pokemonLocalRepository.insert(pokemon)
                            pokemon
                        }
                    )
                } else {
                    pokemonLocalRepository.getPokemonById(id).first()
                }
            }
        }
}