package es.rudo.data.repository.remote

import arrow.core.Either
import es.rudo.data.mappers.toDomain
import es.rudo.data.model.remote.GenericRemoteDto
import es.rudo.data.model.remote.ItemRemoteDto
import es.rudo.data.repository.remote.data_source.ItemRemoteDataSource
import es.rudo.domain.model.Generic
import es.rudo.domain.model.Item
import es.rudo.domain.repository.remote.ItemRemoteRepository
import retrofit2.Response
import es.rudo.data.helpers.Pager as DataPager
import es.rudo.domain.helpers.Pager as DomainPager

class ItemRemoteRepositoryImpl(
    private val itemRemoteDataSource: ItemRemoteDataSource
): ItemRemoteRepository {
    override suspend fun getItems(
        offset: Int, limit: Int
    ): Either<Throwable, DomainPager<Generic>> {
        return Either.catch {
            val response: Response<DataPager<GenericRemoteDto>> =
                itemRemoteDataSource.getItems(
                    offset,
                    limit
                )
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
            } else throw Exception("Se ha produido un error al obtener los datos")
        }
    }

    override suspend fun getItemById(
        id: String
    ): Either<Throwable, Item> {
        return Either.catch {
            val response: Response<ItemRemoteDto> =
                itemRemoteDataSource.getItemById(id)
            if (response.isSuccessful && response.body() != null) {
                val itemRemote = response.body() as ItemRemoteDto
                itemRemote.toDomain()
            } else throw Exception("Se ha produido un error al obtener los datos")
        }
    }
}