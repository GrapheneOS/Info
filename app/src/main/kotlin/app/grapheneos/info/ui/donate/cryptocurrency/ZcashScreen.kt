package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun ZcashScreen(
    showSnackbarError: (String) -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(stringResource(R.string.zcash_info))
        }
        item {
            AddressInfoItem(
                title = stringResource(R.string.zcash_transparent),
                qrCodePainterResourceId = R.drawable.donate_zcash_transparent_qr_code,
                qrCodeContentDescription = stringResource(R.string.zcash_transparent_qr_code_description),
                addressUrl = "zcash:t1SJABjX8rqgzqgrzLW5dUw7ikSDZ2snD8A?label=GrapheneOS%20Foundation&message=Donation%20to%20GrapheneOS%20Foundation",
                address = "t1SJABjX8rqgzqgrzLW5dUw7ikSDZ2snD8A",
                showSnackbarError = showSnackbarError
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
private fun ZcashScreenPreview() {
    MaterialTheme {
        ZcashScreen({})
    }
}