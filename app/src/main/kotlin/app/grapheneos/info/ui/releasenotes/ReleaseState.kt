package app.grapheneos.info.ui.releasenotes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
fun ReleaseState(releaseStates: List<Pair<String, String>>, modifier: Modifier = Modifier) {

    Row(
        modifier = Modifier
            .fillMaxSize(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Column(
        ) {
            ElevatedCard() {
                Text(
                    stringResource(R.string.stable),
                    style = typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
                Text(
                    text = releaseStates.find { it.first == "stable" }?.second.toString(),
                    style = typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
            }
        }
        Column(
        ) {
            ElevatedCard() {
                Text(
                    stringResource(R.string.beta),
                    style = typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
                Text(
                    text = releaseStates.find { it.first == "beta" }?.second.toString(),
                    style = typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
            }
        }
        Column(
        ) {
            ElevatedCard() {
                Text(
                    stringResource(R.string.alpha),
                    style = typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
                Text(
                    text = releaseStates.find { it.first == "alpha" }?.second.toString(),
                    style = typography.bodyMedium,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp).align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
