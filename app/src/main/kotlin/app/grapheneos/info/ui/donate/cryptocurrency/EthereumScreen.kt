package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun EthereumScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("Ethereum can be used to make donations to the non-profit GrapheneOS Foundation.")
        }
        item {
            AddressInfoItem(
                title = "Ethereum",
                qrCodePainterResourceId = R.drawable.donate_ethereum_qr_code,
                qrCodeContentDescription = "Ethereum donation QR code",
                addressUrl = "ethereum:0xC822A62E5Ab443E0001f30cEB9B2336D0524fC61",
                address = "0xC822A62E5Ab443E0001f30cEB9B2336D0524fC61",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            Text(
                "We aren't looking for donations of tokens, only Ethereum itself.",
                fontWeight = FontWeight.Bold
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
private fun EthereumScreenPreview() {
    MaterialTheme {
        EthereumScreen({})
    }
}