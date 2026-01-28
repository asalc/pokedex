package es.shiro.pokedex.data.repository.remote

import android.content.Context
import arrow.core.Either
import es.shiro.pokedex.common.extensions.orDefault
import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.R
import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSource
import es.shiro.pokedex.domain.model.Generic
import es.shiro.pokedex.domain.model.Pokemon
import es.shiro.pokedex.domain.model.PokemonSpecies
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import retrofit2.Response

class PokemonRemoteRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val context: Context
): PokemonRemoteRepository {

    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): Either<Throwable, Pager<Generic>> =
        Either.catch {
            val response: Response<Pager<GenericRemoteDto>> =
                pokemonRemoteDataSource.getPokemon(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                val pager = response.body()
                Pager(
                    count = pager?.count.orDefault(),
                    next = pager?.next.orEmpty(),
                    previous = pager?.previous.orEmpty(),
                    results = pager?.results?.map { it.toDomain() }.orEmpty()
                )
            } else throw Exception(context.getString(R.string.generic_error))
        }

    override suspend fun getPokemonById(
        id: String
    ): Either<Throwable, Pokemon> =
        Either.catch {
            val response: Response<PokemonRemoteDto> =
                pokemonRemoteDataSource.getPokemonById(id)
            if (response.isSuccessful && response.body() != null) {
                (response.body() as PokemonRemoteDto).toDomain()
            } else throw Exception(context.getString(R.string.generic_error))
        }

    override suspend fun getPokemonSpecies(
        id: String
    ): Either<Throwable, PokemonSpecies> =
        Either.catch {
            val response: Response<PokemonSpeciesRemoteDto> =
                pokemonRemoteDataSource.getPokemonSpecies(id)
            if (response.isSuccessful && response.body() != null) {
                (response.body() as PokemonSpeciesRemoteDto).toDomain()
            } else throw Exception(context.getString(R.string.generic_error))
        }
}