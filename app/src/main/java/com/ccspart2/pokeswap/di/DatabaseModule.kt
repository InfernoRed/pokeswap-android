package com.ccspart2.pokeswap.di

import android.content.Context
import androidx.room.Room
import com.ccspart2.pokeswap.data.localData.dataStore.DataStoreManager
import com.ccspart2.pokeswap.data.localData.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun providesDB(@ApplicationContext context: Context): PokemonDatabase {
        return Room.databaseBuilder(context, PokemonDatabase::class.java, "Database").build()
    }

    @Singleton
    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}
