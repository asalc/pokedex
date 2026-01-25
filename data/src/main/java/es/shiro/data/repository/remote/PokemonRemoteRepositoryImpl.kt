package es.shiro.data.repository.remote

import android.content.Context
import arrow.core.Either
import es.shiro.data.R
import es.shiro.data.mappers.toDomain
import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.data.model.remote.PokemonRemoteDto
import es.shiro.data.model.remote.PokemonSpeciesRemoteDto
import es.shiro.data.repository.remote.data_source.PokemonRemoteDataSource
import es.shiro.data.helpers.Pager as DataPager
import es.shiro.domain.helpers.Pager as DomainPager
import es.shiro.domain.model.Generic
import es.shiro.domain.model.Pokemon
import es.shiro.domain.model.PokemonSpecies
import es.shiro.domain.repository.remote.PokemonRemoteRepository
import retrofit2.Response

class PokemonRemoteRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val context: Context
): PokemonRemoteRepository {

    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): Either<Throwable, DomainPager<Generic>> =
        Either.catch {
            val response: Response<DataPager<GenericRemoteDto>> =
                pokemonRemoteDataSource.getPokemon(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                val pager = response.body()
                DomainPager<Generic>().apply {
                    count = pager?.count ?: 0
                    next = pager?.next
                    previous = pager?.previous
                    results = pager?.results?.map {
                        it.toDomain()
                    } as ArrayList
                }
            } else throw Exception(context.getString(R.string.generic_error))
        }

    override suspend fun getPokemonById(
        id: String
    ): Either<Throwable, Pokemon> =
        Either.catch {
            val response: Response<PokemonRemoteDto> =
                pokemonRemoteDataSource.getPokemonById(id)
            if (response.isSuccessful && response.body() != null) {
                val pokemonRemote = response.body() as PokemonRemoteDto
                pokemonRemote.toDomain()
            } else throw Exception(context.getString(R.string.generic_error))
        }

    override suspend fun getPokemonSpecies(
        id: String
    ): Either<Throwable, PokemonSpecies> =
        Either.catch {
            val response: Response<PokemonSpeciesRemoteDto> =
                pokemonRemoteDataSource.getPokemonSpecies(id)
            if (response.isSuccessful && response.body() != null) {
                val pokemonSpeciesRemote = response.body() as PokemonSpeciesRemoteDto
                pokemonSpeciesRemote.toDomain()
            } else throw Exception(context.getString(R.string.generic_error))
        }
}