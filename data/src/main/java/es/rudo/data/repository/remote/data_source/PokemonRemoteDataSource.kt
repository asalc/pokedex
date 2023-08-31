package es.rudo.data.repository.remote.data_source

import es.rudo.data.helpers.Pager
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.PokemonRemoteDto
import es.rudo.data.model.remote.PokemonSpeciesRemoteDto
import retrofit2.Response

interface PokemonRemoteDataSource {

    suspend fun getPokemon(
        offset: Int = 0,
        limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    suspend fun getPokemonById(
        id: String
    ): Response<PokemonRemoteDto>
}