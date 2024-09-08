package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ScreenNavCardItem

@Composable
fun DonateCryptoCurrencyStartScreen(
    onNavigateToBitcoinScreen: () -> Unit,
    onNavigateToMoneroScreen: () -> Unit,
    onNavigateToZcashScreen: () -> Unit,
    onNavigateToEthereumScreen: () -> Unit,
    onNavigateToCardanoScreen: () -> Unit,
    onNavigateToLitecoinScreen: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(bottom = 16.dp, start = 16.dp, end = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.bitcoin),
                title = stringResource(id = R.string.bitcoin)
            ) {
                onNavigateToBitcoinScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.monero),
                title = stringResource(id = R.string.monero)
            ) {
                onNavigateToMoneroScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.zcash),
                title = stringResource(id = R.string.zcash)
            ) {
                onNavigateToZcashScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.ethereum),
                title = stringResource(id = R.string.ethereum)
            ) {
                onNavigateToEthereumScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.cardano),
                title = stringResource(id = R.string.cardano)
            ) {
                onNavigateToCardanoScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.litecoin),
                title = stringResource(id = R.string.litecoin)
            ) {
                onNavigateToLitecoinScreen()
            }
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun DonateCryptoCurrencyStartScreenPreview() {
    MaterialTheme {
        DonateCryptoCurrencyStartScreen({}, {}, {}, {}, {}, {})
    }
}