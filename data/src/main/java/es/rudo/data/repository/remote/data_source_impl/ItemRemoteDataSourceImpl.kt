package es.rudo.data.repository.remote.data_source_impl

import es.rudo.data.api.Api
import es.rudo.data.helpers.Pager
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.ItemRemoteDto
import es.rudo.data.repository.remote.data_source.ItemRemoteDataSource
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