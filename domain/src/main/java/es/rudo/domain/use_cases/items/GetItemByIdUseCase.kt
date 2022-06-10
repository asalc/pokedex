package es.rudo.domain.use_cases.items

import arrow.core.Either
import es.rudo.domain.model.Item
import es.rudo.domain.repository.local.ItemLocalRepository
import es.rudo.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetItemByIdUseCase(
    private val itemLocalRepository: ItemLocalRepository,
    private val itemRemoteRepository: ItemRemoteRepository
) {
    suspend operator fun invoke(id: Int): Either<Throwable, Item> =
        withContext(Dispatchers.IO) {
            return@withContext Either.catch {
                if (itemLocalRepository.getByItemId(id).isEmpty()) {
                    itemRemoteRepository.getItemById(id.toString()).fold(
                        ifLeft = { error -> throw error },
                        ifRight = { item ->
                            itemLocalRepository.insert(item)
                            item
                        }
                    )
                } else {
                    itemLocalRepository.getByItemId(id).first()
                }
            }
        }
}