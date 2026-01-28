package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetItemsUseCaseImpl(
    private val localRepository: ItemLocalRepository,
    private val remoteRepository: ItemRemoteRepository,
    private val getItemByIdUseCase: GetItemByIdUseCase
) : GetItemsUseCase {

    override suspend fun getItems(limit: Int): Either<Throwable, Int> =
        withContext(Dispatchers.IO) {
            Either.catch {
                remoteRepository.getItems().fold(
                    ifLeft = { error -> throw error },
                    ifRight = { itemPager ->
                        if (localRepository.getCount() < limit) {
                            for (i in 1 until limit + 1) {
                                getItemByIdUseCase.getItemById(i)
                            }
                        }
                        itemPager.count
                    }
                )
            }
        }
}