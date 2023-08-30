package es.rudo.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.rudo.data.database.DatabaseConfig

@Entity(tableName = DatabaseConfig.TableNames.POKEMON)
class PokemonLocalDto(

    @ColumnInfo(name = "pokemon_id")
    @PrimaryKey(autoGenerate = false)
    val pokemonId: Int,

    @ColumnInfo(name = "name")
    val name: ArrayList<Pair<String, String>>,

    @ColumnInfo(name = "first_type")
    val firstType: String? = null,

    @ColumnInfo(name = "second_type")
    val secondType: String? = null,

    @ColumnInfo(name = "front_sprite_url")
    val frontSpriteUrl: String,

    @ColumnInfo(name = "back_sprite_url")
    val backSpriteUrl: String
)