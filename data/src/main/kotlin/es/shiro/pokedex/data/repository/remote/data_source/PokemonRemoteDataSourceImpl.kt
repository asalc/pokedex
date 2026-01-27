package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.api.PokedexApi
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
import retrofit2.Response

class PokemonRemoteDataSourceImpl(
    private val pokedexApi: PokedexApi
): PokemonRemoteDataSource {

    override suspend fun getPokemon(
        offset: Int,
        limit: Int
    ): Response<Pager<GenericRemoteDto>> = pokedexApi.getPokemon(offset, limit)

    override suspend fun getPokemonById(
        id: String
    ): Response<PokemonRemoteDto> = pokedexApi.getPokemonById(id)

    override suspend fun getPokemonSpecies(
        id: String
    ): Response<PokemonSpeciesRemoteDto> = pokedexApi.getPokemonSpecies(id)
}