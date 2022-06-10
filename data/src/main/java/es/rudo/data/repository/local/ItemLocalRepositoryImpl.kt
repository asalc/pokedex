package es.rudo.data.repository.local

import es.rudo.data.mappers.toDomain
import es.rudo.data.mappers.toLocalDto
import es.rudo.data.repository.local.data_source.ItemLocalDataSource
import es.rudo.domain.model.Item
import es.rudo.domain.repository.local.ItemLocalRepository

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

    override suspend fun getAll(): Array<Item> {
        return itemLocalDataSource.getAll().map {
            it.toDomain()
        }.toTypedArray()
    }

    override suspend fun getByItemId(id: Int): Array<Item> {
        return itemLocalDataSource.getByItemId(id)
            .map { it.toDomain() }
            .toTypedArray()
    }
}