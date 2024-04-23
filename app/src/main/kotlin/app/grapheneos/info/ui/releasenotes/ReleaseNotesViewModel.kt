package app.grapheneos.info.ui.releasenotes

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownServiceException
import javax.net.ssl.HttpsURLConnection

const val TAG = "ReleaseNotesViewModel"

class ReleaseNotesViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private val _uiState = MutableStateFlow(ReleaseNotesUiState(savedStateHandle))
    val uiState: StateFlow<ReleaseNotesUiState> = _uiState.asStateFlow()

    fun updateReleaseNotes(
        useCaches: Boolean,
        showSnackbarError: suspend (message: String) -> Unit,
        scrollReleaseNotesLazyListTo: (scrollTo: Int) -> Unit,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = URL("https://grapheneos.org/releases.atom")
                val connection = url.openConnection() as HttpsURLConnection

                connection.apply {
                    connectTimeout = 10_000
                    readTimeout = 30_000
                }

                try {
                    connection.useCaches = useCaches

                    connection.connect()

                    val responseText = String(connection.inputStream.readBytes())

                    val entries = "<entry>(.*?)</entry>".toRegex().findAll(responseText).map { it.groups[1]!!.value }

                    // Only update if there are changes to the number of changelogs
                    if ((entries.count() - uiState.value.entries.size) != 0) {
                        _uiState.value.entries.clear()
                        withContext(Dispatchers.Main) {
                            _uiState.value.entries.addAll(entries)
                        }
                    }

                    if (!uiState.value.didInitialScroll) {
                        val scrollTo = uiState.value.entries.indexOfFirst {
                            val title = "<title>(.*?)</title>".toRegex()
                                .find(it)?.groups?.get(1)?.value

                            android.os.Build.VERSION.INCREMENTAL == title
                        }

                        if (scrollTo != -1) {
                            _uiState.value.didInitialScroll = true
                            scrollReleaseNotesLazyListTo(scrollTo)
                        }
                    }
                } catch (e: SocketTimeoutException) {
                    val errorMessage = "Socket Timeout Exception"
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: IOException) {
                    val errorMessage = "Failed to retrieve latest release notes"
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: UnknownServiceException) {
                    val errorMessage = "Unknown Service Exception"
                    Log.e(TAG, "Unknown Service Exception", e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } finally {
                    connection.disconnect()
                }
            } catch (e: IOException) {
                val errorMessage = "Failed to create HttpsURLConnection"
                Log.e(TAG, errorMessage, e)
                viewModelScope.launch {
                    showSnackbarError("$errorMessage: $e")
                }
            }
        }
    }
}
