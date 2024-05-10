package app.grapheneos.info.ui.releasenotes

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import app.grapheneos.info.R
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

class ReleaseNotesViewModel(
    private val application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

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
                    val errorMessage =
                        application.getString(R.string.update_release_notes_socket_timeout_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: IOException) {
                    val errorMessage =
                        application.getString(R.string.update_release_notes_io_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: UnknownServiceException) {
                    val errorMessage =
                        application.getString(R.string.update_release_notes_unknown_service_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } finally {
                    connection.disconnect()
                }
            } catch (e: IOException) {
                val errorMessage =
                    application.getString(R.string.update_release_notes_failed_to_create_httpsurlconnection_snackbar_message)
                Log.e(TAG, errorMessage, e)
                viewModelScope.launch {
                    showSnackbarError("$errorMessage: $e")
                }
            }
        }
    }
}
