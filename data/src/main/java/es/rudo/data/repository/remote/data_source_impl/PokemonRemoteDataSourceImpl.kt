package es.rudo.data.repository.remote.data_source_impl

import es.rudo.data.api.Api
import es.rudo.data.helpers.Pager
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.PokemonRemoteDto
import es.rudo.data.model.remote.PokemonSpeciesRemoteDto
import es.rudo.data.repository.remote.data_source.PokemonRemoteDataSource
import retrofit2.Response

class PokemonRemoteDataSourceImpl(
    private val api: Api
): PokemonRemoteDataSource {

    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): Response<Pager<GenericRemoteDto>> = api.getPokemon(offset, limit)

    override suspend fun getPokemonById(
        id: String
    ): Response<PokemonRemoteDto> = api.getPokemonById(id)
}