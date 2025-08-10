package com.example.movieapp.utils

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SessionManger @Inject constructor(@ApplicationContext private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(Constants.USER_DATA)
        private val userName= stringPreferencesKey(Constants.USER_NAME)
        private val userEmail= stringPreferencesKey(Constants.USER_EMAIL)
    }

    suspend fun saveUserInfo(name: String,email: String) = context.dataStore.edit {
        it[userName] = name
        it[userEmail] = email


    }

    fun getUserInfo() = context.dataStore.data.map {
        it[userName] to it[userEmail]

    }

    suspend fun deleteToken() = context.dataStore.edit {
        it.clear()
    }


}