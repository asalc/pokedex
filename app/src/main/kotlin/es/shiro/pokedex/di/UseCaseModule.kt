package es.shiro.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.shiro.domain.repository.local.ItemLocalRepository
import es.shiro.domain.repository.local.PokemonLocalRepository
import es.shiro.domain.repository.remote.ItemRemoteRepository
import es.shiro.domain.repository.remote.PokemonRemoteRepository
import es.shiro.domain.use_cases.items.GetItemByIdUseCase
import es.shiro.domain.use_cases.items.GetItemsUseCase
import es.shiro.domain.use_cases.pokemon.GetPokemonByIdUseCase
import es.shiro.domain.use_cases.pokemon.GetPokemonSpeciesUseCase
import es.shiro.domain.use_cases.pokemon.GetPokemonUseCase

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    //BERRIES
    /*@Provides
    fun providesGetBerriesUseCase(
        berryLocalRepository: BerryLocalRepository,
        berryRemoteRepository: BerryRemoteRepository,
        getBerryByIdUseCase: GetBerryByIdUseCase
    ): GetBerriesUseCase = GetBerriesUseCase(
        berryLocalRepository,
        berryRemoteRepository,
        getBerryByIdUseCase
    )

    @Provides
    fun providesGetBerryByIdUseCase(
        berryLocalRepository: BerryLocalRepository,
        berryRemoteRepository: BerryRemoteRepository,
        getItemByIdUseCase: GetItemByIdUseCase
    ): GetBerryByIdUseCase = GetBerryByIdUseCase(
        berryLocalRepository,
        berryRemoteRepository,
        getItemByIdUseCase
    )*/

    //ITEMS
    @Provides
    fun providesGetItemsUseCase(
        itemLocalRepository: ItemLocalRepository,
        itemRemoteRepository: ItemRemoteRepository,
        getItemByIdUseCase: GetItemByIdUseCase
    ): GetItemsUseCase = GetItemsUseCase(
        itemLocalRepository,
        itemRemoteRepository,
        getItemByIdUseCase
    )

    @Provides
    fun providesGetItemByIdUseCase(
        itemLocalRepository: ItemLocalRepository,
        itemRemoteRepository: ItemRemoteRepository
    ): GetItemByIdUseCase = GetItemByIdUseCase(
        itemLocalRepository,
        itemRemoteRepository
    )

    //POKEMON
    @Provides
    fun providesGetPokemonUseCase(
        pokemonLocalRepository: PokemonLocalRepository,
        pokemonRemoteRepository: PokemonRemoteRepository,
        getPokemonByIdUseCase: GetPokemonByIdUseCase
    ): GetPokemonUseCase = GetPokemonUseCase(
        pokemonLocalRepository,
        pokemonRemoteRepository,
        getPokemonByIdUseCase
    )

    @Provides
    fun providesGetPokemonByIdUseCase(
        pokemonLocalRepository: PokemonLocalRepository,
        pokemonRemoteRepository: PokemonRemoteRepository,
        getPokemonSpeciesUseCase: GetPokemonSpeciesUseCase
    ): GetPokemonByIdUseCase = GetPokemonByIdUseCase(
        pokemonLocalRepository,
        pokemonRemoteRepository,
        getPokemonSpeciesUseCase
    )

    @Provides
    fun providesGetPokemonSpeciesUseCase(
        pokemonLocalRepository: PokemonLocalRepository,
        pokemonRemoteRepository: PokemonRemoteRepository
    ): GetPokemonSpeciesUseCase = GetPokemonSpeciesUseCase(
        pokemonLocalRepository,
        pokemonRemoteRepository
    )
}