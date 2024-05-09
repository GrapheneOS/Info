package app.grapheneos.info.ui.donate.banktransfers

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
fun BankTransfersScreen() {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
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
                    term = stringResource(R.string.iban),
                    description = stringResource(R.string.eu_sepa_eur_iban)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bic),
                    description = stringResource(R.string.eu_sepa_eur_bic)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.eu_sepa_eur_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = stringResource(R.string.eu_sepa_eur_wise_and_bank_address)
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
                    description = stringResource(R.string.uk_gbp_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bic),
                    description = stringResource(R.string.uk_gbp_bic)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.iban),
                    description = stringResource(R.string.uk_gbp_iban)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.sort_code),
                    description = stringResource(R.string.uk_gbp_sort_code)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.uk_gbp_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = stringResource(R.string.uk_gbp_wise_and_bank_address)
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
                    description = stringResource(R.string.us_usd_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.routing_number),
                    description = stringResource(R.string.us_usd_routing_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.account_type),
                    description = stringResource(R.string.checking)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = stringResource(R.string.us_usd_wise_address)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.us_usd_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = stringResource(R.string.us_usd_bank_address)
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
                    description = stringResource(R.string.australia_aud_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bsb_code),
                    description = stringResource(R.string.australia_aud_bsb_code)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.australia_aud_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = stringResource(R.string.australia_aud_wise_address)
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
                    description = stringResource(R.string.new_zealand_nzd_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = stringResource(R.string.new_zealand_nzd_wise_address)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.new_zealand_nzd_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = stringResource(R.string.new_zealand_nzd_bank_address)
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
                    description = stringResource(R.string.canada_cad_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.transit_number),
                    description = stringResource(R.string.canada_cad_transit_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.institution_number),
                    description = stringResource(R.string.canada_cad_institution_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = stringResource(R.string.canada_cad_wise_address)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.canada_cad_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = stringResource(R.string.canada_cad_bank_address)
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
                    description = stringResource(R.string.hungary_huf_account_number)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.hungary_huf_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_and_bank_address),
                    description = stringResource(R.string.hungary_huf_wise_and_bank_address)
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
                    description = stringResource(R.string.turkey_try_iban)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.wise_address),
                    description = stringResource(R.string.turkey_try_wise_address)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_name),
                    description = stringResource(R.string.turkey_try_bank_name)
                )
                AccountInfoItemEntry(
                    term = stringResource(R.string.bank_address),
                    description = stringResource(R.string.turkey_try_bank_address)
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
            Text(AnnotatedString.Companion.fromHtml(stringResource(R.string.interac_e_transfer_info)))
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