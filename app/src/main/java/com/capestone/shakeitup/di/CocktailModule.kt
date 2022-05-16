package com.capestone.shakeitup.di

import com.capestone.shakeitup.repository.CocktailRepository
import com.capestone.shakeitup.repository.ICocktailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
abstract class CocktailModule {

    // To be read as — When someone asks for DataRepository, create a DataRepoImpl and return it.
    @Binds
    abstract fun bindCocktailRepository(repo: CocktailRepository): ICocktailRepository
}