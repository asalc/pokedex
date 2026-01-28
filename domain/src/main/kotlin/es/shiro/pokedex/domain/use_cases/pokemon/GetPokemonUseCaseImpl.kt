package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonUseCaseImpl(
    private val localRepository: PokemonLocalRepository,
    private val remoteRepository: PokemonRemoteRepository,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) : GetPokemonUseCase {

    override suspend fun getPokemon(limit: Int): Either<Throwable, Int> =
        withContext(Dispatchers.IO) {
            Either.catch {
                remoteRepository.getPokemon().fold(
                    ifLeft = { error -> throw error },
                    ifRight = { pokemonPager ->
                        if (localRepository.getCount() < limit) {
                            for (i in 1 until limit + 1) {
                                getPokemonByIdUseCase.getPokemonById(i)
                            }
                        }
                        pokemonPager.count
                    }
                )
            }
        }
}