package es.shiro.pokedex.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import es.shiro.data.database.PokeDatabase
import es.shiro.data.database.DatabaseConfig
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext
        context: Context
    ): PokeDatabase = Room.databaseBuilder(
        context,
        PokeDatabase::class.java,
        DatabaseConfig.DATABASE_NAME
    ).build()
}