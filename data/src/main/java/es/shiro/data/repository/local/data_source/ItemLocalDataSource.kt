package es.shiro.data.repository.local.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import es.shiro.data.database.DatabaseConfig
import es.shiro.data.model.local.ItemLocalDto

private const val ITEMS = DatabaseConfig.TableNames.ITEMS

@Dao
interface ItemLocalDataSource {

    @Insert
    fun insert(itemLocalDto: ItemLocalDto)

    @Insert
    fun insertAll(vararg itemLocalDto: ItemLocalDto)

    @Delete
    fun delete(itemLocalDto: ItemLocalDto)

    @Query("DELETE FROM $ITEMS")
    fun deleteAll()

    @Query("SELECT * FROM $ITEMS")
    fun getAll(): Array<ItemLocalDto>

    @Query("SELECT * FROM $ITEMS WHERE item_id LIKE :id")
    fun getByItemId(id: Int): Array<ItemLocalDto>

    @Query("SELECT COUNT(*) FROM $ITEMS")
    fun getCount(): Int
}