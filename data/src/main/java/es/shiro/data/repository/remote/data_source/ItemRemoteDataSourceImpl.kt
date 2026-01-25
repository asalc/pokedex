package es.shiro.data.repository.remote.data_source

import es.shiro.data.api.Api
import es.shiro.data.helpers.Pager
import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.data.model.remote.ItemRemoteDto
import retrofit2.Response

class ItemRemoteDataSourceImpl(
    private val api: Api
): ItemRemoteDataSource {

    override suspend fun getItems(
        offset: Int,
        limit: Int
    ): Response<Pager<GenericRemoteDto>> {
        return api.getItems(offset, limit)
    }

    override suspend fun getItemById(
        id: String
    ): Response<ItemRemoteDto> {
        return api.getItemById(id)
    }
}