package es.shiro.pokedex.domain.use_cases.items

import arrow.core.Either
import arrow.core.raise.either
import es.shiro.pokedex.domain.mocks.emptyPager
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoInteractions
import org.mockito.kotlin.whenever

class GetItemsUseCaseImplTest {

    private val localRepository: ItemLocalRepository = mock()
    private val remoteRepository: ItemRemoteRepository = mock()
    private val getItemByIdUseCase: GetItemByIdUseCase = mock<GetItemByIdUseCaseImpl>()

    private lateinit var useCase: GetItemsUseCase

    @BeforeEach
    fun setUp() {
        useCase = GetItemsUseCaseImpl(
            localRepository,
            remoteRepository,
            getItemByIdUseCase
        )
    }

    @Test
    fun `getItems - Success result - limit is greater than localRepository count`() =
        runBlocking {
            // Given
            val limit = 5
            whenever(remoteRepository.getItems(any(), any()))
                .thenReturn(either { emptyPager })
            whenever(localRepository.getCount()).thenReturn(0)

            // When
            val result = useCase.getItems(limit)

            // Then
            assertTrue(result.isRight())
            verify(getItemByIdUseCase, times(limit))
                .getItemById(any())

            return@runBlocking
        }

    @Test
    fun `getItems - Success result - localRepository count is greater than limit`() =
        runBlocking {
            // Given
            val limit = 0
            whenever(remoteRepository.getItems(any(), any()))
                .thenReturn(either { emptyPager })
            whenever(localRepository.getCount()).thenReturn(5)

            // When
            val result = useCase.getItems(limit)

            // Then
            assertTrue(result.isRight())
            verifyNoInteractions(getItemByIdUseCase)

            return@runBlocking
        }

    @Test
    fun `getItems - Error result`() =
        runBlocking {
            // Given
            whenever(remoteRepository.getItems(any(), any()))
                .thenThrow(RuntimeException())

            // When
            lateinit var result: Either<Throwable, Int>
            try {
                result = useCase.getItems(0)
            } catch (_: Exception) {
                // Then
                assertTrue(result.isLeft())
                verifyNoInteractions(localRepository)
                verifyNoInteractions(getItemByIdUseCase)
            }

            return@runBlocking
        }
}