package es.shiro.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.shiro.pokedex.data.repository.local.ItemLocalRepositoryImpl
import es.shiro.pokedex.data.repository.local.data_source.ItemLocalDataSource
import es.shiro.pokedex.data.repository.local.data_source.PokemonLocalDataSource
import es.shiro.pokedex.data.repository.local.PokemonLocalRepositoryImpl
import es.shiro.pokedex.data.repository.remote.ItemRemoteRepositoryImpl
import es.shiro.pokedex.data.repository.remote.PokemonRemoteRepositoryImpl
import es.shiro.pokedex.data.repository.remote.data_source.ItemRemoteDataSource
import es.shiro.pokedex.data.repository.remote.data_source.PokemonRemoteDataSource
import es.shiro.domain.repository.local.ItemLocalRepository
import es.shiro.domain.repository.local.PokemonLocalRepository
import es.shiro.domain.repository.remote.ItemRemoteRepository
import es.shiro.domain.repository.remote.PokemonRemoteRepository

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    //LOCAL
    /*@Provides
    fun providesBerryLocalRepository(
        berryLocalDataSource: BerryLocalDataSource
    ): BerryLocalRepository = BerryLocalRepositoryImpl(berryLocalDataSource)*/

    @Provides
    fun providesItemLocalRepository(
        itemLocalDataSource: ItemLocalDataSource
    ): ItemLocalRepository = ItemLocalRepositoryImpl(itemLocalDataSource)

    @Provides
    fun providesPokemonLocalRepository(
        pokemonLocalDataSource: PokemonLocalDataSource
    ): PokemonLocalRepository = PokemonLocalRepositoryImpl(pokemonLocalDataSource)

    //REMOTE
    /*@Provides
    fun providesBerryRemoteRepository(
        berryRemoteDataSource: BerryRemoteDataSource,
        berryLocalRepository: BerryLocalRepository
    ): BerryRemoteRepository = BerryRemoteRepositoryImpl(
        berryRemoteDataSource,
        berryLocalRepository
    )*/

    @Provides
    fun providesItemRemoteRepository(
        itemRemoteDataSource: ItemRemoteDataSource,
        context: Context
    ): ItemRemoteRepository = ItemRemoteRepositoryImpl(
        itemRemoteDataSource,
        context
    )

    @Provides
    fun providesPokemonRemoteRepository(
        pokemonRemoteDataSource: PokemonRemoteDataSource,
        context: Context
    ): PokemonRemoteRepository = PokemonRemoteRepositoryImpl(
        pokemonRemoteDataSource,
        context
    )
}