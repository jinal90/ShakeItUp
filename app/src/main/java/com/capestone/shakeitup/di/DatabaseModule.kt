package com.capestone.shakeitup.di

import android.content.Context
import com.capestone.shakeitup.data.AppDatabase
import com.capestone.shakeitup.data.CocktailDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideCocktailDao(appDatabase: AppDatabase): CocktailDao {
        return appDatabase.cocktailDao()
    }
}