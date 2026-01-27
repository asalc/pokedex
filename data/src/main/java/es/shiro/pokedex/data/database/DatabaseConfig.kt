package es.shiro.pokedex.data.database

object DatabaseConfig {

    const val DATABASE_VERSION = 4
    const val DATABASE_NAME = "poke-database"

    object TableNames {
        const val ITEMS = "items"
        const val POKEMON = "pokemon"
    }
}