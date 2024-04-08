package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun LitecoinScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("Litecoin can be used to make donations to the non-profit GrapheneOS Foundation.")
        }
        item {
            AddressInfoItem(
                title = "Litecoin",
                qrCodePainterResourceId = R.drawable.donate_litecoin_qr_code,
                qrCodeContentDescription = "Litecoin donation QR code",
                addressUrl = "litecoin:ltc1qzssmqueth6zjzr95rkluy5xdx9q4lk8vyrvea9?label=GrapheneOS%20Foundation&message=Donation%20to%20GrapheneOS%20Foundation",
                address = "ltc1qzssmqueth6zjzr95rkluy5xdx9q4lk8vyrvea9",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            Text("We recommend using the lowest fee tier recommended by your wallet since the transaction taking hours or even a couple days is fine. An even lower custom fee isn't recommended unless you're going to monitor it and bump the fee with RBF.")
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun LitecoinScreenPreview() {
    MaterialTheme {
        LitecoinScreen({})
    }
}