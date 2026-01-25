package es.shiro.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.domain.model.Pokemon
import es.shiro.domain.repository.local.PokemonLocalRepository
import es.shiro.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonByIdUseCase(
    private val pokemonLocalRepository: PokemonLocalRepository,
    private val pokemonRemoteRepository: PokemonRemoteRepository,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase
) {
    suspend operator fun invoke(id: Int): Either<Throwable, Pokemon> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                if (pokemonLocalRepository.getPokemonById(id).isEmpty()) {
                    pokemonRemoteRepository.getPokemonById(id.toString()).fold(
                        ifLeft = { error -> throw error },
                        ifRight = { pokemon ->
                            getPokemonSpeciesUseCase(id.toString()).fold(
                                ifLeft = { error -> throw error },
                                ifRight = { pokemonSpecies ->
                                    pokemon.apply {
                                        this.names = pokemonSpecies?.names
                                        this.pokemonSpecies = pokemonSpecies
                                    }
                                    pokemonLocalRepository.insert(pokemon)
                                }
                            )
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