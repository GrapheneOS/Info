package app.grapheneos.info.ui.donate.banktransfers

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun BankTransfersScreen(
    modifier: Modifier = Modifier,
    additionalContentPadding: PaddingValues = PaddingValues(0.dp)
) {
    ScreenLazyColumn(
        modifier = modifier
            .fillMaxSize(),
        additionalContentPadding = additionalContentPadding
    ) {
        item {
            Text(
                stringResource(R.string.local_bank_transfer_to_wise),
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.eu_sepa_eur)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
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
                    term = stringResource(R.string.bank_name),
                    description = "Wise Europe SA"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = "Rue du Trône 100, 3rd floor\n" +
                            "Brussels\n" +
                            "1050\n" +
                            "Belgium"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.uk_gbp)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
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
                    term = stringResource(R.string.sort_code),
                    description = "23-14-70"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Wise Payments Limited"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = "56 Shoreditch High Street\n" +
                            "London\n" +
                            "E1 6JJ\n" +
                            "United Kingdom"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.us_usd)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
                    description = "8313560023"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.routing_number),
                    description = "026073150"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_type),
                    description = stringResource(R.string.checking)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = "30 W. 26th Street, Sixth Floor\n" +
                            "New York NY\n" +
                            "10010\n" +
                            "United States"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Community Federal Savings Bank"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = "89-16 Jamaica Ave\n" +
                            "Woodhaven NY\n" +
                            "11421\n" +
                            "United States"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.australia_aud)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
                    description = "213524417"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bsb_code),
                    description = "774-001"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Wise Australia Pty Ltd"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = "Suite 1, Level 11, 66 Goulburn Street\n" +
                            "Sydney\n" +
                            "2000\n" +
                            "Australia"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.new_zealand_nzd)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
                    description = "04-2021-0151878-36"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = "56 Shoreditch High Street\n" +
                            "London\n" +
                            "E1 6JJ\n" +
                            "United Kingdom"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "JPMorgan Chase"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = "Head Office, Pwc Tower\n" +
                            "Auckland\n" +
                            "1010\n" +
                            "New Zealand"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.canada_cad)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
                    description = "200110745303"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.transit_number),
                    description = "16001"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.institution_number),
                    description = "621"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = "99 Bank Street, Suite 1420\n" +
                            "Ottawa ON\n" +
                            "K1P 1H4\n" +
                            "Canada"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Peoples Trust"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = "595 Burrard Street\n" +
                            "Vancouver BC\n" +
                            "V7X 1L7\n" +
                            "Canada"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.hungary_huf)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_number),
                    description = "12600016-11020392-99827322"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Wise Europe SA"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = "Rue du Trône 100, 3rd floor\n" +
                            "Brussels\n" +
                            "1050\n" +
                            "Belgium"
                )
            }
        }
        item {
            AccountInfoItem(
                title = stringResource(R.string.turkey_try)
            ) {
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_holder),
                    description = stringResource(R.string.grapheneos_foundation)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.iban),
                    description = "TR43 0010 3000 0000 0057 4294 70"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = "56 Shoreditch High Street, London, E1 6JJ, United Kingdom"
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = "Fibabanka A.Ş."
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = "Büyükdere Cad. 129, Esentepe Mah., Sisli"
                )
            }
        }
        item {
            Text(
                stringResource(R.string.interac_e_transfer),
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            SelectionContainer {
                Text(AnnotatedString.Companion.fromHtml(stringResource(R.string.interac_e_transfer_info)))
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
private fun BankTransfersScreenPreview() {
    MaterialTheme {
        BankTransfersScreen()
    }
}