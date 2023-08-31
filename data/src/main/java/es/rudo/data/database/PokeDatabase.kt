package es.rudo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.rudo.data.model.local.ItemLocalDto
import es.rudo.data.model.local.PokemonLocalDto
import es.rudo.data.repository.local.data_source.ItemLocalDataSource
import es.rudo.data.repository.local.data_source.PokemonLocalDataSource

@Database(
    entities = [
        //BerryLocalDto::class,
        ItemLocalDto::class,
        PokemonLocalDto::class
    ],
    version = DatabaseConfig.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class PokeDatabase: RoomDatabase() {
    //abstract fun berryDao(): BerryLocalDataSource
    abstract fun itemDao(): ItemLocalDataSource
    abstract fun pokemonDao(): PokemonLocalDataSource
}