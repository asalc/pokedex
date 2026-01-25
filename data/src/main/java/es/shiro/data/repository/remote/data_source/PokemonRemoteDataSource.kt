package es.shiro.data.repository.remote.data_source

import es.shiro.data.helpers.Pager
import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.data.model.remote.PokemonRemoteDto
import es.shiro.data.model.remote.PokemonSpeciesRemoteDto
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemon(
        offset: Int = 0,
        limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    suspend fun getPokemonById(
        id: String
    ): Response<PokemonRemoteDto>

    suspend fun getPokemonSpecies(
        id: String
    ): Response<PokemonSpeciesRemoteDto>
}