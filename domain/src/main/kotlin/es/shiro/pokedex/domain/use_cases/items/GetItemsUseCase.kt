package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either

interface GetItemsUseCase {
    suspend fun getItems(limit: Int): Either<Throwable, Int>
}