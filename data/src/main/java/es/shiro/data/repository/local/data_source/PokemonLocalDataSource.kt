package es.shiro.data.repository.local.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.shiro.data.database.DatabaseConfig
import es.shiro.data.model.local.PokemonLocalDto

private const val POKEMON = DatabaseConfig.TableNames.POKEMON

@Dao
interface PokemonLocalDataSource {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pokemon: PokemonLocalDto)

    @Insert
    fun insertAll(vararg pokemonLocalDto: PokemonLocalDto)

    @Delete
    fun delete(pokemon: PokemonLocalDto)

    @Query("DELETE from $POKEMON")
    fun deleteAll()

    @Query("SELECT * FROM $POKEMON")
    fun getAll(): Array<PokemonLocalDto>

    @Query("SELECT * FROM $POKEMON WHERE pokemon_id LIKE :id")
    fun getPokemonById(id: Int): Array<PokemonLocalDto>

    @Query("SELECT COUNT(*) FROM $POKEMON")
    fun getCount(): Int
}