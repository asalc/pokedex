package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetItemByIdUseCaseImpl @Inject constructor(
    private val localRepository: ItemLocalRepository,
    private val remoteRepository: ItemRemoteRepository
) : GetItemByIdUseCase {

    override suspend fun getItemById(id: Int): Either<Throwable, Item> =
        withContext(Dispatchers.IO) {
            Either.catch {
                localRepository.getItemById(id) ?:
                    remoteRepository.getItemById(id.toString()).fold(
                        ifLeft = { error -> throw error },
                        ifRight = { item ->
                            localRepository.insert(item)
                            item
                        }
                    )
            }
        }
}