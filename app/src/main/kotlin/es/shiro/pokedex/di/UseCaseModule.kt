package es.shiro.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.shiro.pokedex.domain.repository.local.ItemLocalRepository
import es.shiro.pokedex.domain.repository.local.PokemonLocalRepository
import es.shiro.pokedex.domain.repository.remote.ItemRemoteRepository
import es.shiro.pokedex.domain.repository.remote.PokemonRemoteRepository
import es.shiro.pokedex.domain.use_cases.items.GetItemByIdUseCase
import es.shiro.pokedex.domain.use_cases.items.GetItemByIdUseCaseImpl
import es.shiro.pokedex.domain.use_cases.items.GetItemsUseCase
import es.shiro.pokedex.domain.use_cases.items.GetItemsUseCaseImpl
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonByIdUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonByIdUseCaseImpl
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonSpeciesUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonSpeciesUseCaseImpl
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonUseCase
import es.shiro.pokedex.domain.use_cases.pokemon.GetPokemonUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    //ITEMS
    @Provides
    fun providesGetItemsUseCase(
        localRepository: ItemLocalRepository,
        remoteRepository: ItemRemoteRepository,
        getItemByIdUseCase: GetItemByIdUseCaseImpl
    ): GetItemsUseCase = GetItemsUseCaseImpl(
        localRepository,
        remoteRepository,
        getItemByIdUseCase
    )

    @Provides
    fun providesGetItemByIdUseCase(
        localRepository: ItemLocalRepository,
        remoteRepository: ItemRemoteRepository
    ): GetItemByIdUseCase = GetItemByIdUseCaseImpl(
        localRepository,
        remoteRepository
    )

    //POKEMON
    @Provides
    fun providesGetPokemonUseCase(
        localRepository: PokemonLocalRepository,
        remoteRepository: PokemonRemoteRepository,
        getPokemonByIdUseCase: GetPokemonByIdUseCaseImpl
    ): GetPokemonUseCase = GetPokemonUseCaseImpl(
        localRepository,
        remoteRepository,
        getPokemonByIdUseCase
    )

    @Provides
    fun providesGetPokemonByIdUseCase(
        localRepository: PokemonLocalRepository,
        remoteRepository: PokemonRemoteRepository,
        getPokemonSpeciesUseCase: GetPokemonSpeciesUseCaseImpl
    ): GetPokemonByIdUseCase = GetPokemonByIdUseCaseImpl(
        localRepository,
        remoteRepository,
        getPokemonSpeciesUseCase
    )

    @Provides
    fun providesGetPokemonSpeciesUseCase(
        localRepository: PokemonLocalRepository,
        remoteRepository: PokemonRemoteRepository
    ): GetPokemonSpeciesUseCase = GetPokemonSpeciesUseCaseImpl(
        localRepository,
        remoteRepository
    )
}