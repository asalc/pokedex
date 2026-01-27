package es.shiro.pokedex.domain.repository.remote

import arrow.core.Either
import es.shiro.pokedex.common.helpers.Pager
import es.shiro.pokedex.domain.model.Generic
import es.shiro.pokedex.domain.model.Item

interface ItemRemoteRepository {

    suspend fun getItems(
        offset: Int = 0,
        limit: Int = 1
    ): Either<Throwable, Pager<Generic>>

    suspend fun getItemById(
        id: String
    ): Either<Throwable, Item>
}