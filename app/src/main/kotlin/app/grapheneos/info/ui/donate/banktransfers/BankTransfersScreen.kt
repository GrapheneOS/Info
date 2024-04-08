package app.grapheneos.info.ui.donate.banktransfers

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun BankTransfersScreen() {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                "Local Bank Transfer to Wise",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
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
        item {
            AccountInfoItem(
                title = "UK (GBP)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "49883070"
                )
                AccountInfoItemEntry(
                    term = "BIC",
                    description = "TRWIBEB1XXX"
                )
                AccountInfoItemEntry(
                    term = "IBAN",
                    description = "GB68 TRWI 2314 7049 8830 70"
                )
                AccountInfoItemEntry(
                    term = "Sort code",
                    description = "23-14-70"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "Wise Payments Limited"
                )
                AccountInfoItemEntry(
                    term = "Wise and Bank address",
                    description = "56 Shoreditch High Street\n" +
                            "London\n" +
                            "E1 6JJ\n" +
                            "United Kingdom"
                )
            }
        }
        item {
            AccountInfoItem(
                title = "US (USD)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "8313560023"
                )
                AccountInfoItemEntry(
                    term = "Routing number",
                    description = "026073150"
                )
                AccountInfoItemEntry(
                    term = "Account type",
                    description = "Checking"
                )
                AccountInfoItemEntry(
                    term = "Wise address",
                    description = "30 W. 26th Street, Sixth Floor\n" +
                            "New York NY\n" +
                            "10010\n" +
                            "United States"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "Community Federal Savings Bank"
                )
                AccountInfoItemEntry(
                    term = "Bank address",
                    description = "89-16 Jamaica Ave\n" +
                            "Woodhaven NY\n" +
                            "11421\n" +
                            "United States"
                )
            }
        }
        item {
            AccountInfoItem(
                title = "Australia (AUD)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "213524417"
                )
                AccountInfoItemEntry(
                    term = "BSB code",
                    description = "774-001"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "Wise Australia Pty Ltd"
                )
                AccountInfoItemEntry(
                    term = "Wise address",
                    description = "Suite 1, Level 11, 66 Goulburn Street\n" +
                            "Sydney\n" +
                            "2000\n" +
                            "Australia"
                )
            }
        }
        item {
            AccountInfoItem(
                title = "New Zealand (NZD)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "04-2021-0151878-36"
                )
                AccountInfoItemEntry(
                    term = "Wise address",
                    description = "56 Shoreditch High Street\n" +
                            "London\n" +
                            "E1 6JJ\n" +
                            "United Kingdom"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "JPMorgan Chase"
                )
                AccountInfoItemEntry(
                    term = "Bank address",
                    description = "Head Office, Pwc Tower\n" +
                            "Auckland\n" +
                            "1010\n" +
                            "New Zealand"
                )
            }
        }
        item {
            AccountInfoItem(
                title = "Canada (CAD)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "200110745303"
                )
                AccountInfoItemEntry(
                    term = "Transit number",
                    description = "16001"
                )
                AccountInfoItemEntry(
                    term = "Institution number",
                    description = "621"
                )
                AccountInfoItemEntry(
                    term = "Wise address",
                    description = "99 Bank Street, Suite 1420\n" +
                            "Ottawa ON\n" +
                            "K1P 1H4\n" +
                            "Canada"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "Peoples Trust"
                )
                AccountInfoItemEntry(
                    term = "Bank address",
                    description = "595 Burrard Street\n" +
                            "Vancouver BC\n" +
                            "V7X 1L7\n" +
                            "Canada"
                )
            }
        }
        item {
            AccountInfoItem(
                title = "Hungary (HUF)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "Account number",
                    description = "12600016-11020392-99827322"
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
        item {
            AccountInfoItem(
                title = "Turkey (TRY)"
            ) {
                AccountInfoItemEntry(
                    term = "Account holder",
                    description = "GrapheneOS Foundation"
                )
                AccountInfoItemEntry(
                    term = "IBAN",
                    description = "TR43 0010 3000 0000 0057 4294 70"
                )
                AccountInfoItemEntry(
                    term = "Wise address",
                    description = "56 Shoreditch High Street, London, E1 6JJ, United Kingdom"
                )
                AccountInfoItemEntry(
                    term = "Bank name",
                    description = "Fibabanka A.Ş."
                )
                AccountInfoItemEntry(
                    term = "Bank address",
                    description = "Büyükdere Cad. 129, Esentepe Mah., Sisli"
                )
            }
        }
        item {
            Text(
                "Interac e-Transfer",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Text(
                buildAnnotatedString {
                    append("If you have a Canadian bank account, you can send Canadian dollar donations to the non-profit GrapheneOS Foundation via Interac e-Transfer to ")

                    pushStyle(SpanStyle(fontStyle = FontStyle.Italic))

                    append("contact@grapheneos.org")

                    pop()

                    append(". The email address has Interac e-Transfer Autodeposit support enabled so no security question is necessary. If your bank doesn't support Autodeposit, set the answer to the security question to GrapheneOS.")
                }
            )
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun BankTransfersScreenPreview() {
    MaterialTheme {
        BankTransfersScreen()
    }
}