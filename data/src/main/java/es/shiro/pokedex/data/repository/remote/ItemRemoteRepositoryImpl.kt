package es.shiro.pokedex.data.repository.remote

import android.content.Context
import arrow.core.Either
import es.shiro.pokedex.data.R
import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.domain.model.Generic
import es.shiro.domain.model.Item
import es.shiro.domain.repository.remote.ItemRemoteRepository
import retrofit2.Response
import es.shiro.pokedex.data.helpers.Pager as DataPager
import es.shiro.domain.helpers.Pager as DomainPager

class ItemRemoteRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val context: Context
): ItemRemoteRepository {

    override suspend fun getItems(
        offset: Int, limit: Int
    ): Either<Throwable, DomainPager<Generic>> =
        Either.catch {
            val response: Response<DataPager<GenericRemoteDto>> =
                itemRemoteDataSource.getItems(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                val pager = response.body()
                DomainPager<Generic>().apply {
                    count = pager?.count ?: 0
                    next = pager?.next
                    previous = pager?.previous
                    results = pager?.results?.map {
                        it.toDomain()
                    } as ArrayList
                }
            } else throw Exception(context.getString(R.string.generic_error))
        }

    override suspend fun getItemById(
        id: String
    ): Either<Throwable, Item> =
        Either.catch {
            val response: Response<ItemRemoteDto> =
                itemRemoteDataSource.getItemById(id)
            if (response.isSuccessful && response.body() != null) {
                val itemRemote = response.body() as ItemRemoteDto
                itemRemote.toDomain()
            } else throw Exception(context.getString(R.string.generic_error))
        }
}