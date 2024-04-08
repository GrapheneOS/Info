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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun PaypalScreen() {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("PayPal can be used to make one-time, monthly or yearly donations to the non-profit GrapheneOS Foundation.")
        }
        item {
            Text("If possible, use the donation link for your currency. If it's not listed, please use the CAD donation link.")
        }
        item {
            Text(
                "Donation links",
                Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.AttachMoney,
                name = "Canadian Dollar (CAD)",
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=T8KRPYKU5QVNE"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.AttachMoney,
                name = "United States dollar (USD)",
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=2S2BP8V4E7PXU"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.Euro,
                name = "Euro (EUR)",
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=5SNPWEDS53HW4"
            )
        }
        item {
            LinkCardItem(
                Icons.Filled.CurrencyPound,
                name = "British pound (GBP)",
                tint = true,
                link = "https://www.paypal.com/donate/?hosted_button_id=N498QNB7NPKU8"
            )
        }
        item {
            Text("PayPal charges a base fee of 30 cents and 2.9% of the donation amount within Canada. There's an additional 0.8% fee for donations from the US and 1% for other countries. Currency conversion adds an additional 4% fee as opposed to the usual PayPal conversion fee of 3%.")
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