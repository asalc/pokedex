package es.shiro.pokedex.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import es.shiro.pokedex.App

@Module
@InstallIn(SingletonComponent::class)
class ContextModule {

    @Provides
    fun providesContext(): Context = App.instance
}