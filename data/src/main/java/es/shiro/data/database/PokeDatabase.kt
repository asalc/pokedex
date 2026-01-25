package es.shiro.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.shiro.data.model.local.ItemLocalDto
import es.shiro.data.model.local.PokemonLocalDto
import es.shiro.data.repository.local.data_source.ItemLocalDataSource
import es.shiro.data.repository.local.data_source.PokemonLocalDataSource

@Database(
    entities = [
        ItemLocalDto::class,
        PokemonLocalDto::class
    ],
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = false
)

@TypeConverters(DatabaseTypeConverters::class)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun itemDao(): ItemLocalDataSource
    abstract fun pokemonDao(): PokemonLocalDataSource
}