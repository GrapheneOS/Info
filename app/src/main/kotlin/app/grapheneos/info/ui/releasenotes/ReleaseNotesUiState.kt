package app.grapheneos.info.ui.releasenotes

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf

data class ReleaseNotesUiState(
    val entries: MutableList<String> = mutableStateListOf(),
    val updateReleaseNotesSwitch: MutableState<Boolean> = mutableStateOf(false)
)
