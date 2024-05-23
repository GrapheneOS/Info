package app.grapheneos.info.ui.releasenotes

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable

@OptIn(SavedStateHandleSaveableApi::class)
class ReleaseNotesUiState(savedStateHandle: SavedStateHandle) {
    var didInitialScroll: Boolean by savedStateHandle.saveable {
        mutableStateOf(false)
    }
    /** Unsorted release notes, use .toSortedMap().toList().asReversible() to get them in the proper order. */
    val entries: MutableMap<String, String> = mutableStateMapOf()
}