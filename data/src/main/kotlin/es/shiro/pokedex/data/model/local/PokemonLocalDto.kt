package es.shiro.pokedex.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.shiro.pokedex.common.extensions.EMPTY_STRING
import es.shiro.pokedex.data.database.DatabaseConfig
import es.shiro.pokedex.domain.model.PokemonSpecies

@Entity(tableName = DatabaseConfig.TableNames.POKEMON)
class PokemonLocalDto(

    @ColumnInfo(name = "pokemon_id")
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,

    @ColumnInfo(name = "names")
    val names: List<Pair<String, String>> = emptyList(),

    @ColumnInfo(name = "first_type")
    val firstType: String = String.EMPTY_STRING,

    @ColumnInfo(name = "second_type")
    val secondType: String = String.EMPTY_STRING,

    @ColumnInfo(name = "front_sprite_url")
    val frontSpriteUrl: String = String.EMPTY_STRING,

    @ColumnInfo(name = "back_sprite_url")
    val backSpriteUrl: String = String.EMPTY_STRING,

    @ColumnInfo(name = "species")
    val species: PokemonSpecies = PokemonSpecies()
)