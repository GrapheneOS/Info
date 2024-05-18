package app.grapheneos.info.preferences

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import app.grapheneos.info.dataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PreferencesViewModel(private val application: Application) : AndroidViewModel(application) {
    /**
     * Preferences state
     */
    private val _uiState = MutableStateFlow(PreferencesUiState())
    val uiState: StateFlow<PreferencesUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            // Populate the values of the preferences from the Preferences DataStore.
            application.dataStore.data.map { preferences ->
                fun getPreferencePair(preference: Pair<Preferences.Key<String>, MutableState<String>>): Pair<Preferences.Key<String>, MutableState<String>> {
                    return Pair(
                        preference.first,
                        mutableStateOf(
                            preferences[preference.first] ?: preference.second.value
                        )
                    )
                }

                _uiState.update {
                    PreferencesUiState(
                        startDestination = getPreferencePair(uiState.value.startDestination)
                    )
                }
            }.collect()
        }
    }

    /**
     * Set a preference to a value and save to Preferences DataStore
     */
    fun setPreference(key: Preferences.Key<String>, value: String) {
        viewModelScope.launch {
            application.dataStore.edit { preferences ->
                preferences[key] = value
            }
        }
    }
}