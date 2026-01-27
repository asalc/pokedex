package es.shiro.pokedex.data.repository.remote

import android.content.Context
import arrow.core.Either
import es.shiro.pokedex.common.extensions.orDefault
import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.data.R
import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.model.remote.GenericRemoteDto
import es.shiro.pokedex.data.model.remote.ItemRemoteDto
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.pokedex.domain.model.Generic
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import retrofit2.Response

class ItemRemoteRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource,
    private val context: Context
): ItemRemoteRepository {

    override suspend fun getItems(
        offset: Int, limit: Int
    ): Either<Throwable, Pager<Generic>> =
        Either.catch {
            val response: Response<Pager<GenericRemoteDto>> =
                itemRemoteDataSource.getItems(offset, limit)
            if (response.isSuccessful && response.body() != null) {
                val pager = response.body()
                Pager<Generic>(
                    count = pager?.count.orDefault(),
                    next = pager?.next,
                    previous = pager?.previous,
                    results = pager?.results?.map { it.toDomain() }
                )
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