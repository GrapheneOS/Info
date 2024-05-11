package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun BitcoinScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(stringResource(R.string.bitcoin_info))
        }
        item {
            AddressInfoItem(
                title = "Bech32 (Segwit)",
                qrCodePainterResourceId = R.drawable.donate_bitcoin_qr_code,
                qrCodeContentDescription = stringResource(R.string.bech32_segwit_qr_code_description),
                addressUrl = "bitcoin:bc1q9qw3g8tdxf3dugkv2z8cahd3axehph0mhsqk96?label=GrapheneOS%20Foundation&message=Donation%20to%20GrapheneOS%20Foundation",
                address = "bc1q9qw3g8tdxf3dugkv2z8cahd3axehph0mhsqk96",
                showSnackbarError = showSnackbarError,
            )
        }
        item {
            AddressInfoItem(
                title = "Bech32m (Taproot)",
                qrCodePainterResourceId = R.drawable.donate_bitcoin_taproot_qr_code,
                qrCodeContentDescription = stringResource(R.string.bech32m_taproot_qr_code_description),
                addressUrl = "bitcoin:bc1prqf5hks5dnd4j87wxw3djn20559yhj7wvvcv6fqxpwlg96udkzgqtamhry?label=GrapheneOS%20Foundation&message=Donation%20to%20GrapheneOS%20Foundation",
                address = "bc1prqf5hks5dnd4j87wxw3djn20559yhj7wvvcv6fqxpwlg96udkzgqtamhry",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            AddressInfoItem(
                title = "BIP47 payment code (stealth address)",
                qrCodePainterResourceId = R.drawable.donate_bitcoin_bip47_qr_code,
                qrCodeContentDescription = stringResource(R.string.bip47_payment_code_stealth_address_qr_code_description),
                addressUrl = "bitcoin:PM8TJKmhJNQX6UTFagyuBk8UGmwKM6yDovEokpHBscPgP3Ac7WdK5zaQKh5XLSawyxiGYZS2a7HkAoeL6oHg7Ahn1VXX888yRG4PwF1dojouPtW7tEHT",
                address = "PM8TJKmhJNQX6UTFagyuBk8UGmwKM6yDovEokpHBscPgP3Ac7WdK5zaQKh5XLSawyxiGYZS2a7HkAoeL6oHg7Ahn1VXX888yRG4PwF1dojouPtW7tEHT",
                showSnackbarError = showSnackbarError
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    SelectionContainer {
                        Text(
                            buildAnnotatedString {
                                append("PayNym: ")

                                pushStyle(SpanStyle(fontStyle = FontStyle.Italic))

                                append("+GrapheneOS")

                                pop()
                            }
                        )
                    }
                }
            }
        }
        item {
            Text(stringResource(R.string.bitcoin_fee_notice))
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun BitcoinScreenPreview() {
    MaterialTheme {
        BitcoinScreen({})
    }
}