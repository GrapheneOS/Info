package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun CardanoScreen(
    modifier: Modifier = Modifier,
    showSnackbarError: (String) -> Unit = {},
    additionalContentPadding: PaddingValues = PaddingValues(0.dp)
) {
    ScreenLazyColumn(
        modifier = modifier
            .fillMaxSize(),
        additionalContentPadding = additionalContentPadding
    ) {
        item {
            Text(stringResource(R.string.cardano_info))
        }
        item {
            AddressInfoItem(
                title = "Cardano",
                qrCodePainterResourceId = R.drawable.donate_cardano_qr_code,
                qrCodeContentDescription = stringResource(R.string.cardano_qr_code_description),
                addressUrl = "web+cardano:addr1q9v89vfwyfssveug5zf2w7leafz8ethq490gvq0ghag883atfnucytpnq2t38dj7cnyngs6ne05cdwu9gseevgmt3ggq2a2wt6",
                address = "addr1q9v89vfwyfssveug5zf2w7leafz8ethq490gvq0ghag883atfnucytpnq2t38dj7cnyngs6ne05cdwu9gseevgmt3ggq2a2wt6",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            SelectionContainer {
                Text(
                    AnnotatedString.Companion.fromHtml(stringResource(R.string.cardano_handle_notice))
                )
            }
        }
        item {
            Text(
                stringResource(R.string.cardano_token_donation_notice),
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
        CardanoScreen()
    }
}