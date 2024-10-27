package app.grapheneos.info.ui.releasenotes

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReleaseNotesScreen(
    modifier: Modifier = Modifier,
    entries: List<Pair<String, String>>,
    updateReleaseNotes: (useCaches: Boolean, finishedUpdating: () -> Unit) -> Unit,
    lazyListState: LazyListState,
    additionalContentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    val localUriHandler = LocalUriHandler.current

    val refreshCoroutineScope = rememberCoroutineScope()

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                refreshCoroutineScope.launch {
                    updateReleaseNotes(true) {}
                }
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    var isRefreshing by remember { mutableStateOf(false) }

    val state = remember {
        object : PullToRefreshState {
            private val anim = Animatable(0f, Float.VectorConverter)

            override val distanceFraction
                get() = anim.value

            override suspend fun animateToThreshold() {
                anim.animateTo(1f, spring())
            }

            override suspend fun animateToHidden() {
                anim.animateTo(0f)
            }

            override suspend fun snapTo(targetValue: Float) {
                anim.snapTo(targetValue)
            }
        }
    }

    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            updateReleaseNotes(false) {
                isRefreshing = false

                refreshCoroutineScope.launch {
                    state.animateToHidden()
                }
            }
        },
        state = state,
        modifier = modifier
            .fillMaxSize()
    ) {
        ScreenLazyColumn(
            modifier = Modifier
                .fillMaxSize(),
            state = lazyListState,
            additionalContentPadding = additionalContentPadding,
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

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(onClick = { localUriHandler.openUri("https://grapheneos.org/releases") }) {
                        Text(text = "See all release notes")
                    }
                }
            }
        }
    }
}