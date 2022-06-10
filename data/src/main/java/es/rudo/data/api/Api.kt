package es.rudo.data.api

import es.rudo.data.helpers.Pager
import es.rudo.data.model.remote.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    //BERRIES
    /*@GET("berry")
    suspend fun getBerries(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    @GET("berry/{id}")
    suspend fun getBerryById(
        @Path("id") id: String
    ): Response<BerryRemoteDto>*/

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
    /*@GET("pokemon")
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
    ): Response<PokemonSpeciesRemoteDto>*/
}