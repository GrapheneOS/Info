package app.grapheneos.info

import android.content.Context
import android.net.http.HttpResponseCache
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.viewmodel.compose.viewModel
import app.grapheneos.info.preferences.PreferencesViewModel
import app.grapheneos.info.ui.theme.InfoTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val preferencesViewModel: PreferencesViewModel = viewModel()

            val preferencesUiState by preferencesViewModel.uiState.collectAsState()

            InfoTheme {
                /** Wait for preferences to load before loading the app to avoid race conditions */
                if (preferencesUiState.isPreferencesLoaded.value) {
                    InfoApp()
                }
            }
        }
    }

    private val cacheFlushCoroutineScope = CoroutineScope(Dispatchers.IO)

    override fun onStop() {
        super.onStop()

        cacheFlushCoroutineScope.launch {
            val cache = HttpResponseCache.getInstalled()
            cache?.flush()
        }
    }
}
