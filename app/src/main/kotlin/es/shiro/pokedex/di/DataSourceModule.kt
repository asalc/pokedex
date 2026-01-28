package es.shiro.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.shiro.pokedex.data.api.PokedexApi
import es.shiro.pokedex.data.database.PokemonDataBase
import es.shiro.pokedex.data.repository.local.data_source.ItemLocalDataSource
import es.shiro.pokedex.data.repository.local.data_source.PokemonLocalDataSource
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSourceImpl
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun providesItemLocalDataSource(
        dataBase: PokemonDataBase
    ): ItemLocalDataSource = dataBase.itemDao()

    @Provides
    fun providesPokemonLocalDataSource(
        dataBase: PokemonDataBase
    ): PokemonLocalDataSource = dataBase.pokemonDao()

    @Provides
    fun providesItemRemoteDataSource(
        pokedexApi: PokedexApi
    ): ItemRemoteDataSource = ItemRemoteDataSourceImpl(pokedexApi)

    @Provides
    fun providesPokemonRemoteDataSource(
        pokedexApi: PokedexApi
    ): PokemonRemoteDataSource = PokemonRemoteDataSourceImpl(pokedexApi)
}