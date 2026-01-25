package es.shiro.data.model.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import es.shiro.data.database.DatabaseConfig

@Entity(tableName = DatabaseConfig.TableNames.ITEMS)
class ItemLocalDto(

    @ColumnInfo(name = "item_id")
    @PrimaryKey(autoGenerate = false)
    val itemId: Int,

    @ColumnInfo(name = "name")
    val name: ArrayList<Pair<String, String>>,

    @ColumnInfo(name = "cost")
    val cost: Int,

    @ColumnInfo(name = "sprite_url")
    val spriteUrl: String
)