package es.rudo.data.repository.remote.data_source

import es.rudo.data.helpers.Pager
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.ItemRemoteDto
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