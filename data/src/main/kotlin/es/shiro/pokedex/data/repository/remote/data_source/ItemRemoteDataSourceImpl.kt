package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.api.PokedexApi
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import retrofit2.Response

class ItemRemoteDataSourceImpl(
    private val pokedexApi: PokedexApi
): ItemRemoteDataSource {

    override suspend fun getItems(
        offset: Int,
        limit: Int
    ): Response<Pager<GenericRemoteDto>> {
        return pokedexApi.getItems(offset, limit)
    }

    override suspend fun getItemById(
        id: String
    ): Response<ItemRemoteDto> {
        return pokedexApi.getItemById(id)
    }
}