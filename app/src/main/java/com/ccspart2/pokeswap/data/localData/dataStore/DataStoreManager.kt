package com.ccspart2.pokeswap.data.localData.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreManager(private val context: Context) {
    companion object {
        val FAVORITE_POKEMON_ID = stringPreferencesKey("FAVORITE_POKEMON_ID")
    }

    suspend fun saveToDataStore(userPreferences: UserPreferences) {
        context.dataStore.edit {
            it[FAVORITE_POKEMON_ID] = userPreferences.favPokemonId
        }
    }

    fun getFromDataStore() = context.dataStore.data.map {
        UserPreferences(
            favPokemonId = it[FAVORITE_POKEMON_ID] ?: "",
        )
    }
}
