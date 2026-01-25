package es.shiro.domain.repository.remote

import arrow.core.Either
import es.shiro.domain.helpers.Pager
import es.shiro.domain.model.Generic
import es.shiro.domain.model.Item

interface ItemRemoteRepository {

    suspend fun getItems(
        offset: Int = 0,
        limit: Int = 1
    ): Either<Throwable, Pager<Generic>>

    suspend fun getItemById(
        id: String
    ): Either<Throwable, Item>
}