package app.grapheneos.info.ui.donate.banktransfers

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp

@Composable
fun AccountInfoItemEntry(
    term: String,
    description: String,
) {
    Column {
        Text(
            text = term,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = description,
        )
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
            title = "EU/SEPA (EUR)"
        ) {
            AccountInfoItemEntry(
                term = "Account holder",
                description = "GrapheneOS Foundation"
            )
            AccountInfoItemEntry(
                term = "IBAN",
                description = "BE20 9677 1140 7056"
            )
            AccountInfoItemEntry(
                term = "BIC",
                description = "TRWIBEB1XXX"
            )
            AccountInfoItemEntry(
                term = "Bank name",
                description = "Wise Europe SA"
            )
            AccountInfoItemEntry(
                term = "Wise and Bank address",
                description = "Rue du Trône 100, 3rd floor\n" +
                        "Brussels\n" +
                        "1050\n" +
                        "Belgium"
            )
        }
    }
}