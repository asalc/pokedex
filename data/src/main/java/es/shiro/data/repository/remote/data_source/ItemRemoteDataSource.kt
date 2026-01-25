package es.shiro.data.repository.remote.data_source

import es.shiro.data.helpers.Pager
import es.shiro.data.model.remote.GenericRemoteDto
import es.shiro.data.model.remote.ItemRemoteDto
import retrofit2.Response

interface ItemRemoteDataSource {

    suspend fun getItems(
        offset: Int = 0,
        limit: Int = 1
    ): Response<Pager<GenericRemoteDto>>

    suspend fun getItemById(
        id: String
    ): Response<ItemRemoteDto>
}