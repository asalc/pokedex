package es.rudo.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.data.api.Api
import es.rudo.data.database.PokeDatabase
import es.rudo.data.repository.local.data_source.ItemLocalDataSource
import es.rudo.data.repository.local.data_source.PokemonLocalDataSource
import es.rudo.data.repository.remote.data_source.ItemRemoteDataSource
import es.rudo.data.repository.remote.data_source.PokemonRemoteDataSource
import es.rudo.data.repository.remote.data_source_impl.ItemRemoteDataSourceImpl
import es.rudo.data.repository.remote.data_source_impl.PokemonRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    //LOCAL
    /*@Provides
    fun providesBerryLocalDataSource(
        pokeDatabase: PokeDatabase
    ): BerryLocalDataSource = pokeDatabase.berryDao()*/

    @Provides
    fun providesItemLocalDataSource(
        pokeDatabase: PokeDatabase
    ): ItemLocalDataSource = pokeDatabase.itemDao()

    @Provides
    fun providesPokemonLocalDataSource(
        pokeDatabase: PokeDatabase
    ): PokemonLocalDataSource = pokeDatabase.pokemonDao()

    //REMOTE
    /*@Provides
    fun providesBerryRemoteDataSource(
        api: Api
    ): BerryRemoteDataSource = BerryRemoteDataSourceImpl(api)*/

    @Provides
    fun providesItemRemoteDataSource(
        api: Api
    ): ItemRemoteDataSource = ItemRemoteDataSourceImpl(api)

    @Provides
    fun providesPokemonRemoteDataSource(
        api: Api
    ): PokemonRemoteDataSource = PokemonRemoteDataSourceImpl(api)
}