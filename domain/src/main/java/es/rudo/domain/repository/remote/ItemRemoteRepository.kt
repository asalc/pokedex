package es.rudo.domain.repository.remote

import arrow.core.Either
import es.rudo.domain.helpers.Pager
import es.rudo.domain.model.Generic
import es.rudo.domain.model.Item

interface ItemRemoteRepository {

    suspend fun getItems(
        offset: Int = 0,
        limit: Int = 1
    ): Either<Throwable, Pager<Generic>>

    suspend fun getItemById(
        id: String
    ): Either<Throwable, Item>
}