package app.grapheneos.info.ui.donate.banktransfers

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R

@Composable
fun WiseQuickLinkCard(
    modifier: Modifier = Modifier,
    showSnackbarError: (String) -> Unit = {}
) {
    val wiseQuickLinkUrl = "https://wise.com/pay/business/grapheneosfoundation"
    val uriHandler = LocalUriHandler.current
    val openUriIllegalArgumentExceptionSnackbarError =
        stringResource(R.string.browser_link_illegal_argument_exception_snackbar_error)

    ElevatedCard(
        onClick = {
            try {
                uriHandler.openUri(wiseQuickLinkUrl)
            } catch (_: IllegalArgumentException) {
                showSnackbarError(openUriIllegalArgumentExceptionSnackbarError)
            }
        },
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = stringResource(R.string.wise_quick_link_title),
                Modifier.padding(bottom = 24.dp),
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = stringResource(R.string.wise_quick_link_description)
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
fun WiseQuickLinkCardPreview() {
    MaterialTheme {
        WiseQuickLinkCard()
    }
}
