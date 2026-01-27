package es.shiro.pokedex.domain.use_cases.pokemon

import arrow.core.Either
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonUseCase(
    private val pokemonLocalRepository: PokemonLocalRepository,
    private val pokemonRemoteRepository: PokemonRemoteRepository,
    private val getPokemonByIdUseCase: GetPokemonByIdUseCase
) {
    suspend operator fun invoke(limit: Int): Either<Throwable, Int> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                pokemonRemoteRepository.getPokemon().fold(
                    ifLeft = { error -> throw error },
                    ifRight = { pokemonPager ->
                        if (pokemonLocalRepository.getCount() < limit) {
                            for (i in 1 until limit + 1) {
                                getPokemonByIdUseCase(i)
                            }
                        }
                        pokemonPager.count
                    }
                )
            }
        }
}