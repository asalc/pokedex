package es.rudo.domain.use_cases.items

import arrow.core.Either
import es.rudo.domain.helpers.Pager
import es.rudo.domain.model.Generic
import es.rudo.domain.repository.local.ItemLocalRepository
import es.rudo.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetItemsUseCase(
    private val itemLocalRepository: ItemLocalRepository,
    private val itemRemoteRepository: ItemRemoteRepository,
    private val getItemByIdUseCase: GetItemByIdUseCase
) {
    suspend operator fun invoke(limit: Int): Either<Throwable, Int> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                itemRemoteRepository.getItems().fold(
                    ifLeft = { error -> throw error },
                    ifRight = { itemPager ->
                        if (itemLocalRepository.getCount() < limit) {
                            for (i in 1 until limit + 1) {
                                getItemByIdUseCase(i)
                            }
                        }
                        itemPager.count
                    }
                )
            }
        }
}