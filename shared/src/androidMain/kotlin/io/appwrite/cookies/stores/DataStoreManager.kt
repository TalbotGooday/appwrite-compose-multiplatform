package io.appwrite.cookies.stores

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first


class DataStoreManager(private val dataStore: DataStore<Preferences>) {

    private val key = stringPreferencesKey("cookies")

    suspend fun read(): String? {
        val preferences = dataStore.data.first()
        return preferences[key]
    }

    suspend fun write(value: String) {
        dataStore.edit { preferences ->
            preferences[key] = value
        }
    }
}