package app.grapheneos.info.ui.donate.banktransfers

import android.content.ClipData
import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ClipEntry
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import kotlinx.coroutines.launch

@Composable
fun AccountInfoItemEntry(
    term: String,
    description: String,
) {
    val clipboard = LocalClipboard.current
    val clipboardScope = rememberCoroutineScope()

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(0.90f)
        ) {
            Text(
                text = term,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
            )
        }
        IconButton(
            onClick = {
                clipboardScope.launch {
                    clipboard.setClipEntry(ClipEntry(ClipData.newPlainText(term, description)))
                }
            },
            modifier = Modifier.weight(0.10f)
        ) {
            Icon(
                imageVector = Icons.Filled.ContentCopy,
                contentDescription = stringResource(R.string.account_info_item_copy_term, term)
            )
        }
    }
}

@Composable
fun AccountInfoItem(
    title: String,
    content: @Composable () -> Unit,
) {
    SelectionContainer {
        ElevatedCard(Modifier.fillMaxWidth()) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    title,
                    Modifier
                        .padding(bottom = 24.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun AccountInfoItemPreview() {
    MaterialTheme {
        AccountInfoItem(
            title = "Title"
        ) {
            AccountInfoItemEntry(
                term = "Account holder",
                description = "Example"
            )
            AccountInfoItemEntry(
                term = "Special Numbers",
                description = "39438293483924"
            )
            AccountInfoItemEntry(
                term = "Etc",
                description = "another description"
            )
        }
    }
}