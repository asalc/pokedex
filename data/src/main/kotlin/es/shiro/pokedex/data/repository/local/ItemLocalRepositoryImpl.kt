package es.shiro.pokedex.data.repository.local

import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.mappers.toLocalDto
import es.shiro.pokedex.data.repository.local.data_source.ItemLocalDataSource
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository

class ItemLocalRepositoryImpl(
    private val localDataSource: ItemLocalDataSource
): ItemLocalRepository {

    override suspend fun insert(item: Item) =
        localDataSource.insert(
            item.toLocalDto()
        )

    override suspend fun getItemById(id: Int): Item? =
        localDataSource.getByItemId(id)?.toDomain()

    override suspend fun getCount(): Int =
        localDataSource.getCount()
}