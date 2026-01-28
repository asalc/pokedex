package es.shiro.pokedex.data.repository.local.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.shiro.pokedex.data.database.DatabaseConfig
import es.shiro.pokedex.data.model.local.PokemonLocalDto

private const val POKEMON = DatabaseConfig.TableNames.POKEMON

@Dao
interface PokemonLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonLocalDto)

    @Query("SELECT * FROM $POKEMON WHERE pokemon_id LIKE :id")
    fun getPokemonById(id: Int): PokemonLocalDto?

    @Query("SELECT COUNT(*) FROM $POKEMON")
    fun getCount(): Int
}