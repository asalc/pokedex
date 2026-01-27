package es.shiro.pokedex.domain.repository.local

import es.shiro.pokedex.domain.model.Item

interface ItemLocalRepository {

    suspend fun insert(item: Item)

    suspend fun insertAll(vararg item: Item)

    suspend fun delete(item: Item)

    suspend fun deleteAll()

    suspend fun getAll(): List<Item>

    suspend fun getByItemId(id: Int): List<Item>

    suspend fun getCount(): Int
}