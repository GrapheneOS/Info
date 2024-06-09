package app.grapheneos.info.ui.releasenotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReleaseNotesScreen(
    entries: List<Pair<String, String>>,
    updateReleaseNotes: (useCaches: Boolean, finishedUpdating: () -> Unit) -> Unit,
    lazyListState: LazyListState,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                updateReleaseNotes(true) {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state = rememberPullToRefreshState()
    if (state.isRefreshing) {
        LaunchedEffect(true) {
            updateReleaseNotes(false) {
                state.endRefresh()
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(state.nestedScrollConnection)
    ) {
        ScreenLazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState,
            verticalArrangement = Arrangement.Top
        ) {
            items(
                items = entries,
                key = { it.first }) {
                Changelog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    it.second
                )
            }
        }
        if ((state.progress > 0.0) || (state.isRefreshing)) {
            PullToRefreshContainer(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .statusBarsPadding(),
                state = state
            )
        }
    }
}