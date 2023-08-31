package es.rudo.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.rudo.pokedex.App

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    fun providesContext(): Context = App.instance
}