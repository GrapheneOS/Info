package app.grapheneos.info.ui.releases

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
import org.grapheneos.tls.ModernTLSSocketFactory
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.URL
import java.net.UnknownServiceException
import javax.net.ssl.HttpsURLConnection

const val TAG = "ReleasesViewModel"

class ReleasesViewModel(
    private val application: Application,
    savedStateHandle: SavedStateHandle
) : AndroidViewModel(application) {

    private val tlsSocketFactory = ModernTLSSocketFactory()
    private val _uiState = MutableStateFlow(ReleasesUiState(savedStateHandle))
    val uiState: StateFlow<ReleasesUiState> = _uiState.asStateFlow()

    init {
        updateChangelog(
            useCaches = true,
            showSnackbarError = {},
            scrollChangelogLazyListTo = {},
            countAsInitialScroll = false,
            onFinishedUpdating = {},
        )
        updateReleaseStates(
            useCaches = true,
            showSnackbarError = {},
            onFinishedUpdating = {},
        )
    }

    fun updateChangelog(
        useCaches: Boolean,
        showSnackbarError: suspend (message: String) -> Unit,
        scrollChangelogLazyListTo: (scrollTo: Int) -> Unit,
        countAsInitialScroll: Boolean = true,
        onFinishedUpdating: () -> Unit = {},
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val url = URL("https://grapheneos.org/releases.atom")
                val connection = url.openConnection() as HttpsURLConnection

                connection.apply {
                    sslSocketFactory = tlsSocketFactory
                    connectTimeout = 10_000
                    readTimeout = 30_000
                }

                try {
                    connection.useCaches = useCaches

                    connection.connect()

                    val responseText = String(connection.inputStream.readBytes())

                    var newEntries = "<entry>(.*?)</entry>".toRegex().findAll(responseText).map { it.groups[1]!!.value }.map { entry ->
                        Pair("<id>(.*?)</id>".toRegex().find(entry)?.groups?.get(1)?.value ?: entry.hashCode().toString(), entry)
                    }.toMap()

                    val osIncrementalVersion = android.os.Build.VERSION.INCREMENTAL

                    val titleValue = fun(entry : Pair<String, String>): String? {
                        return "<title>(.*?)</title>".toRegex()
                        .find(entry.second)?.groups?.get(1)?.value
                    }

                    var currentOsChangelogIndex = newEntries.toSortedMap().toList().asReversed().indexOfFirst { entry ->
                        titleValue(entry) == osIncrementalVersion
                    }

                    if (currentOsChangelogIndex == -1) {
                        currentOsChangelogIndex = newEntries.toSortedMap().toList().asReversed().indexOfFirst { entry ->
                            val incrementedTitle = titleValue(entry)?.replace(Regex("""(\d+)$""")) { matchResult ->
                                val num = matchResult.value.toInt()
                                (num + 1).toString()
                            }

                            incrementedTitle == osIncrementalVersion
                        }

                        if (currentOsChangelogIndex == -1) {
                            currentOsChangelogIndex = 0
                        }
                    }

                    newEntries = newEntries.toSortedMap().toList().asReversed().filterIndexed { index, _ ->
                        index <= currentOsChangelogIndex + 3
                    }.toMap()

                    // Only update if there are changes to the number of changelogs
                    if ((newEntries.count() - uiState.value.entries.size) != 0) {
                        withContext(Dispatchers.Main) {
                            _uiState.value.entries.filterKeys {
                                !newEntries.keys.contains(it)
                            }.forEach {
                                _uiState.value.entries.remove(it.key)
                            }
                            _uiState.value.entries.putAll(newEntries)
                        }
                    }

                    if (countAsInitialScroll && !uiState.value.didInitialScroll) {
                        _uiState.value.didInitialScroll = true
                        scrollChangelogLazyListTo(currentOsChangelogIndex)
                    }
                } catch (e: SocketTimeoutException) {
                    val errorMessage =
                        application.getString(R.string.update_changelog_socket_timeout_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: IOException) {
                    val errorMessage =
                        application.getString(R.string.update_changelog_io_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } catch (e: UnknownServiceException) {
                    val errorMessage =
                        application.getString(R.string.update_changelog_unknown_service_exception_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } finally {
                    connection.disconnect()
                }
            } catch (e: IOException) {
                val errorMessage =
                    application.getString(R.string.update_changelog_failed_to_create_httpsurlconnection_snackbar_message)
                Log.e(TAG, errorMessage, e)
                viewModelScope.launch {
                    showSnackbarError("$errorMessage: $e")
                }
            } finally {
                onFinishedUpdating()
            }
        }
    }

    fun updateReleaseStates(
        useCaches: Boolean,
        showSnackbarError: suspend (message: String) -> Unit,
        onFinishedUpdating: () -> Unit = {},
    ) {
        var board = android.os.Build.BOARD
        val releasePhases = arrayOf("stable", "beta", "alpha")
        for (releasePhase in releasePhases) {
            viewModelScope.launch(Dispatchers.IO) {
                try {
                    val url = URL("https://releases.grapheneos.org/$board-$releasePhase")
                    val connection = url.openConnection() as HttpsURLConnection

                    connection.apply {
                        sslSocketFactory = tlsSocketFactory
                        connectTimeout = 10_000
                        readTimeout = 30_000
                    }

                    try {
                        connection.useCaches = useCaches

                        connection.connect()

                        val responseText = String(connection.inputStream.readBytes())
                        Log.e(TAG, responseText);

                        withContext(Dispatchers.Main) {
                            _uiState.value.releaseStates[releasePhase] = responseText.split(" ")[0]
                        }

                        connection.disconnect()
                    } catch (e: SocketTimeoutException) {
                        val errorMessage =
                            application.getString(R.string.update_release_states_socket_timeout_exception_snackbar_message)
                        Log.e(TAG, errorMessage, e)
                        viewModelScope.launch {
                            showSnackbarError("$errorMessage: $e")
                        }
                    } catch (e: IOException) {
                        val errorMessage =
                            application.getString(R.string.update_release_states_io_exception_snackbar_message)
                        Log.e(TAG, errorMessage, e)
                        viewModelScope.launch {
                            showSnackbarError("$errorMessage: $e")
                        }
                    } catch (e: UnknownServiceException) {
                        val errorMessage =
                            application.getString(R.string.update_release_states_unknown_service_exception_snackbar_message)
                        Log.e(TAG, errorMessage, e)
                        viewModelScope.launch {
                            showSnackbarError("$errorMessage: $e")
                        }
                    } finally {
                        connection.disconnect()
                    }
                } catch (e: IOException) {
                    val errorMessage =
                        application.getString(R.string.update_release_states_failed_to_create_httpsurlconnection_snackbar_message)
                    Log.e(TAG, errorMessage, e)
                    viewModelScope.launch {
                        showSnackbarError("$errorMessage: $e")
                    }
                } finally {
                    onFinishedUpdating()
                }
            }
        }
    }
}