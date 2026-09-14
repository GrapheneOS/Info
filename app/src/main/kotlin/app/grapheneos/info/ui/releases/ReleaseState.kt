package app.grapheneos.info.ui.releases

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R

@Composable
fun ReleaseState(releaseStates: List<Pair<String, String>>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        val releasePhases = mapOf(
            "stable" to R.string.stable,
            "beta" to R.string.beta,
            "alpha" to R.string.alpha,
        )
        for ((releasePhase, resourceId) in releasePhases) {
            Column (
                modifier = Modifier.weight(1f),
            ) {
                ElevatedCard (
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = resourceId),
                        style = typography.titleMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = releaseStates.find { it.first == releasePhase }?.second.toString(),
                        style = typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}