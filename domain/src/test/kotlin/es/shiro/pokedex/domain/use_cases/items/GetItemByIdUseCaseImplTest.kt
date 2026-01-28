package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either
import arrow.core.raise.either
import es.shiro.pokedex.domain.mocks.emptyItem
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class GetItemByIdUseCaseImplTest {

    private val localRepository: ItemLocalRepository = mock()
    private val remoteRepository: ItemRemoteRepository = mock()

    private lateinit var useCase: GetItemByIdUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetItemByIdUseCaseImpl(
            localRepository = localRepository,
            remoteRepository = remoteRepository
        )
    }

    @Test
    fun `getItemById - Found in local repository`() =
        runBlocking {
            // Given
            whenever(localRepository.getItemById(any()))
                .thenReturn(emptyItem)

            // When
            val result = useCase.getItemById(0).getOrNull()

            // Then
            assertTrue(result != null)
            verifyNoInteractions(remoteRepository)

            return@runBlocking
        }

    @Test
    fun `getItemById - Not found in local repository, remote repository success`() =
        runBlocking {
            // Given
            whenever(localRepository.getItemById(any()))
                .thenReturn(null)
            whenever(remoteRepository.getItemById(any()))
                .thenReturn(either { emptyItem })

            // When
            val result = useCase.getItemById(0).getOrNull()

            // Then
            assertTrue(result != null)
            assertEquals(emptyItem.id, result?.id)

            return@runBlocking
        }

    @Test
    fun `getItemById - Not found in local repository, remote repository error`() =
        runBlocking {
            // Given
            whenever(localRepository.getItemById(any()))
                .thenReturn(null)
            whenever(remoteRepository.getItemById(any()))
                .thenThrow(RuntimeException())

            // When
            lateinit var result: Either<Throwable, Item>
            try {
                result = useCase.getItemById(0)
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
                verify(localRepository).insert(emptyItem)
            }

            return@runBlocking
        }
}