package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun CardanoScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("Cardano can be used to make donations to the non-profit GrapheneOS Foundation.")
        }
        item {
            AddressInfoItem(
                title = "Cardano",
                qrCodePainterResourceId = R.drawable.donate_cardano_qr_code,
                qrCodeContentDescription = "Cardano donation QR code",
                addressUrl = "web+cardano:addr1q9v89vfwyfssveug5zf2w7leafz8ethq490gvq0ghag883atfnucytpnq2t38dj7cnyngs6ne05cdwu9gseevgmt3ggq2a2wt6",
                address = "addr1q9v89vfwyfssveug5zf2w7leafz8ethq490gvq0ghag883atfnucytpnq2t38dj7cnyngs6ne05cdwu9gseevgmt3ggq2a2wt6",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            SelectionContainer {
                Text(
                    buildAnnotatedString {
                        append("We own the ")

                        pushStyle(SpanStyle(fontStyle = FontStyle.Italic))

                        append("\$grapheneos")

                        pop()

                        append(" handle with this address so you can also send to the handle.")
                    }
                )
            }
        }
        item {
            Text(
                "We aren't looking for donations of tokens, only Cardano itself.",
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
private fun CardanoScreenPreview() {
    MaterialTheme {
        CardanoScreen({})
    }
}