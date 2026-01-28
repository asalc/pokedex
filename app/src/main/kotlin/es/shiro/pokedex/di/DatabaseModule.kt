package es.shiro.pokedex.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.shiro.pokedex.data.database.PokemonDataBase
import es.shiro.pokedex.data.database.DatabaseConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext
        context: Context
    ): PokemonDataBase = Room.databaseBuilder(
        context,
        PokemonDataBase::class.java,
        DatabaseConfig.DATABASE_NAME
    ).build()
}