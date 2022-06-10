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
    suspend operator fun invoke(): Either<Throwable, Pager<Generic>> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                if (itemLocalRepository.getAll().isEmpty()) {
                    itemRemoteRepository.getItems().fold(
                        ifLeft = { error -> throw error },
                        ifRight = { itemPager ->
                            itemPager
                        }
                    ).apply {
                        if (itemLocalRepository.getAll().isEmpty()) {
                            for (i in 1..20) {
                                getItemByIdUseCase(id = i)
                            }
                        }
                    }
                }
                else {
                    Pager()
                }
            }
        }
}