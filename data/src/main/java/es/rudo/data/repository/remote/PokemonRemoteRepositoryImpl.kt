package es.rudo.data.repository.remote

import android.content.Context
import arrow.core.Either
import es.rudo.data.R
import es.rudo.data.mappers.toDomain
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.PokemonRemoteDto
import es.rudo.data.model.remote.PokemonSpeciesRemoteDto
import es.rudo.data.repository.remote.data_source.PokemonRemoteDataSource
import es.rudo.data.helpers.Pager as DataPager
import es.rudo.domain.helpers.Pager as DomainPager
import es.rudo.domain.model.Generic
import es.rudo.domain.model.Pokemon
import es.rudo.domain.model.PokemonSpecies
import es.rudo.domain.repository.remote.PokemonRemoteRepository
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