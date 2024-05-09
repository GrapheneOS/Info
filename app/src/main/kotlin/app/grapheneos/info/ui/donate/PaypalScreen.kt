package app.grapheneos.info.ui.donate

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.CurrencyPound
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun PaypalScreen() {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(stringResource(R.string.paypal_info_description_part_1))
        }
        item {
            Text(stringResource(R.string.paypal_info_description_part_2))
        }
        item {
            Text(
                stringResource(R.string.donation_links),
                Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.AttachMoney,
                name = stringResource(R.string.canadian_dollar_cad),
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=T8KRPYKU5QVNE"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.AttachMoney,
                name = stringResource(R.string.united_states_dollar_usd),
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=2S2BP8V4E7PXU"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.Euro,
                name = stringResource(R.string.euro_eur),
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=5SNPWEDS53HW4"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.CurrencyPound,
                name = stringResource(R.string.british_pound_gbp),
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=N498QNB7NPKU8"
            )
        }
        item {
            Text(stringResource(R.string.paypal_fee_description))
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun PaypalScreenPreview() {
    MaterialTheme {
        PaypalScreen()
    }
}