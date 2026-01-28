package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetPokemonByIdUseCaseImpl @Inject constructor(
    private val localRepository: PokemonLocalRepository,
    private val remoteRepository: PokemonRemoteRepository,
    private val getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase
) : GetPokemonByIdUseCase {

    override suspend fun getPokemonById(id: Int): Either<Throwable, Pokemon> =
        withContext(Dispatchers.IO) {
            Either.catch {
                localRepository.getPokemonById(id) ?:
                    remoteRepository.getPokemonById(id.toString()).fold(
                        ifLeft = { error -> throw error },
                        ifRight = { pokemon ->
                            getPokemonSpeciesUseCase.getPokemonSpecies(id.toString()).fold(
                                ifLeft = { error -> throw error },
                                ifRight = { pokemonSpecies ->
                                    pokemon.apply {
                                        this.names = pokemonSpecies.names
                                        this.pokemonSpecies = pokemonSpecies
                                    }
                                    localRepository.insert(pokemon)
                                }
                            )
                            localRepository.insert(pokemon)
                            pokemon
                        }
                    )
            }
        }
}