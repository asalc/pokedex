package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either
import es.shiro.pokedex.domain.model.Item

interface GetItemByIdUseCase {
    suspend fun getItemById(id: Int): Either<Throwable, Item>
}