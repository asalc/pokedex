package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.pokedex.data.api.Api
import es.shiro.pokedex.data.helpers.Pager
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
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