package es.shiro.data.repository.remote.data_source

import es.shiro.data.api.Api
import es.shiro.data.helpers.Pager
import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.data.model.remote.PokemonRemoteDto
import es.shiro.data.model.remote.PokemonSpeciesRemoteDto
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

    override suspend fun getPokemonSpecies(
        id: String
    ): Response<PokemonSpeciesRemoteDto> = api.getPokemonSpecies(id)
}