package es.shiro.pokedex.data.repository.local

import es.shiro.pokedex.data.mappers.toDomain
import es.shiro.pokedex.data.mappers.toLocalDto
import es.shiro.pokedex.data.repository.local.data_source.ItemLocalDataSource
import es.shiro.pokedex.domain.model.Item
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository

class ItemLocalRepositoryImpl(
    private val itemLocalDataSource: ItemLocalDataSource
): ItemLocalRepository {

    override suspend fun insert(item: Item) {
        itemLocalDataSource.insert(
            item.toLocalDto()
        )
    }

    override suspend fun insertAll(vararg item: Item) {
        itemLocalDataSource.insertAll(
            *item.map {
                it.toLocalDto()
            }.toTypedArray()
        )
    }

    override suspend fun delete(item: Item) {
        itemLocalDataSource.delete(
            item.toLocalDto()
        )
    }

    override suspend fun deleteAll() {
        itemLocalDataSource.deleteAll()
    }

    override suspend fun getAll(): List<Item> {
        return itemLocalDataSource.getAll().map { it.toDomain() }
    }

    override suspend fun getByItemId(id: Int): List<Item> {
        return itemLocalDataSource.getByItemId(id).map { it.toDomain() }
    }

    override suspend fun getCount(): Int =
        itemLocalDataSource.getCount()
}