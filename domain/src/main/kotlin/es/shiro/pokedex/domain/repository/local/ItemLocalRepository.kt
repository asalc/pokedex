package es.shiro.pokedex.domain.repository.local

import es.shiro.pokedex.domain.model.Item

interface ItemLocalRepository {

    suspend fun insert(item: Item)

    suspend fun getItemById(id: Int): Item?

    suspend fun getCount(): Int
}