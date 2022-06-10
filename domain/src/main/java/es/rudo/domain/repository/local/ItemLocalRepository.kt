package es.rudo.domain.repository.local

import es.rudo.domain.model.Item

interface ItemLocalRepository {

    suspend fun insert(item: Item)

    suspend fun insertAll(vararg item: Item)

    suspend fun delete(item: Item)

    suspend fun deleteAll()

    suspend fun getAll(): Array<Item>

    suspend fun getByItemId(id: Int): Array<Item>
}