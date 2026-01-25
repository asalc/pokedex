package es.shiro.pokedex.data.repository.remote.data_source

import es.shiro.data.api.Api
import es.shiro.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.data.repository.remote.data_source.ItemRemoteDataSourceImpl
import org.junit.jupiter.api.BeforeEach
import org.mockito.kotlin.mock

class ItemRemoteDataSourceImplTest {

    private val api: Api = mock()

    private lateinit var remoteSource: ItemRemoteDataSource

    @BeforeEach
    fun setUp() {
        remoteSource = ItemRemoteDataSourceImpl(api)
    }


}