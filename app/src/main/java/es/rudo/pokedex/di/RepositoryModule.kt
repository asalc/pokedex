package es.rudo.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.data.repository.local.ItemLocalRepositoryImpl
import es.rudo.data.repository.local.data_source.ItemLocalDataSource
import es.rudo.data.repository.remote.ItemRemoteRepositoryImpl
import es.rudo.data.repository.remote.PokemonRemoteRepositoryImpl
import es.rudo.data.repository.remote.data_source.ItemRemoteDataSource
import es.rudo.data.repository.remote.data_source.PokemonRemoteDataSource
import es.rudo.domain.repository.local.ItemLocalRepository
import es.rudo.domain.repository.remote.ItemRemoteRepository
import es.rudo.domain.repository.remote.PokemonRemoteRepository

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

    /*@Provides
    fun providesPokemonLocalRepository(
        pokemonLocalDataSource: PokemonLocalDataSource
    ): PokemonLocalRepository = PokemonLocalRepositoryImpl(pokemonLocalDataSource)*/

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