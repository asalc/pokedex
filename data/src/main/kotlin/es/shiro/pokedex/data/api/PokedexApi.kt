package es.shiro.pokedex.data.api

import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonRemoteDto
import es.shiro.pokedex.data.model.remote.PokemonSpeciesRemoteDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokedexApi {

    //ITEMS
    @GET("item")
    suspend fun getItems(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    @GET("item/{id}")
    suspend fun getItemById(
        @Path("id") id: String
    ): Response<ItemRemoteDto>

    //POKEMON
    @GET("pokemon")
    suspend fun getPokemon(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    @GET("pokemon/{id}")
    suspend fun getPokemonById(
        @Path("id") id: String
    ): Response<PokemonRemoteDto>

    @GET("pokemon-species/{id}")
    suspend fun getPokemonSpecies(
        @Path("id") id: String
    ): Response<PokemonSpeciesRemoteDto>
}