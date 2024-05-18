package app.grapheneos.info.preferences

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import app.grapheneos.info.InfoAppScreens

/** Preference pairs.
 *  The first is the preference key, while the second is the default value. */
data class PreferencesUiState(
    /** Start destination of NavHost **/
    val startDestination: Pair<Preferences.Key<String>, MutableState<String>> = Pair(
        stringPreferencesKey("START_DESTINATION"),
        mutableStateOf(InfoAppScreens.Community.name),
    ),
)