package com.ccspart2.pokeswap.data.localData.dataStore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

class DataStoreManager(private val context: Context) {
    companion object {
        val FAVORITE_POKEMON_ID = stringPreferencesKey("FAVORITE_POKEMON_ID")
        val IS_SELECTED_CURRENCY_EURO = booleanPreferencesKey("IS_SELECTED_CURRENCY_EURO")
    }

    suspend fun saveFavPokemonToDataStore(favPokemonId: String) {
        context.dataStore.edit {
            it[FAVORITE_POKEMON_ID] = favPokemonId
        }
    }

    fun getFavPokemonFromDataStore() = context.dataStore.data.map {
        it[FAVORITE_POKEMON_ID] ?: ""
    }

    suspend fun saveCurrencySelectionToDataStore(isEuro: Boolean) {
        context.dataStore.edit {
            it[IS_SELECTED_CURRENCY_EURO] = isEuro
        }
    }

    fun getSelectedCurrencyFromDataStore() = context.dataStore.data.map {
        it[IS_SELECTED_CURRENCY_EURO] ?: true
    }
}
