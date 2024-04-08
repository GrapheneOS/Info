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
fun MoneroScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("Monero can be used to make donations to the non-profit GrapheneOS Foundation.")
        }
        item {
            AddressInfoItem(
                title = "Monero",
                qrCodePainterResourceId = R.drawable.donate_monero_qr_code,
                qrCodeContentDescription = "Monero donation QR code",
                addressUrl = "monero:862CebHaBpFPgYoNC6zw4U8rsXrDjD8s5LMJNS7yVCRHMUKr9dDi7adMSLUMjkDYJ85xahQTCJHHyK5RCvvRJu9x7iSzN9D?recipient_name=GrapheneOS%20Foundation&tx_description=Donation%20to%20GrapheneOS%20Foundation",
                address = "862CebHaBpFPgYoNC6zw4U8rsXrDjD8s5LMJNS7yVCRHMUKr9dDi7adMSLUMjkDYJ85xahQTCJHHyK5RCvvRJu9x7iSzN9D",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            Text("We recommend using the lowest fee level since it will still be quick.")
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun MoneroScreenPreview() {
    MaterialTheme {
        MoneroScreen({})
    }
}