package app.grapheneos.info.ui.donate

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ClickableText
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn
import app.grapheneos.info.ui.reusablecomposables.ScreenNavCardItem

@OptIn(ExperimentalTextApi::class)
@Composable
fun DonateStartScreen(
    onNavigateToGithubSponsorsScreen: () -> Unit,
    onNavigateToCryptocurrenciesScreen: () -> Unit,
    onNavigateToPayPalScreen: () -> Unit,
    onNavigateToBankTransfersScreen: () -> Unit,
) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text("GrapheneOS is an open source project supported via donations from individuals, companies and other organizations.")
        }
        item {
            Text("Donations are used for paying developers, purchasing hardware (workstations, test devices, debugging cables/boards, etc.), paying for infrastructure (domains, virtual/dedicated servers) and paying legal fees.")
        }
        item {
            Text(
                "Donate the way you want",
                modifier = Modifier.padding(top = 16.dp),
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Text("GrapheneOS offers the following ways to donate.\nChoose the one you prefer.")
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.github),
                name = stringResource(id = R.string.github_sponsors)
            ) {
                onNavigateToGithubSponsorsScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.crypto),
                name = stringResource(id = R.string.cryptocurrencies)
            ) {
                onNavigateToCryptocurrenciesScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.paypal),
                name = stringResource(id = R.string.paypal)
            ) {
                onNavigateToPayPalScreen()
            }
        }
        item {
            ScreenNavCardItem(
                painter = painterResource(id = R.drawable.bank),
                name = stringResource(id = R.string.bank_transfers)
            ) {
                onNavigateToBankTransfersScreen()
            }
        }
        item {
            val localUriHandler = LocalUriHandler.current
            val annotatedString = buildAnnotatedString {
                append("Contribute in other ways?\n")
                append("Check out our ")

                val hiringUrl = "https://grapheneos.org/hiring"
                pushUrlAnnotation(UrlAnnotation(hiringUrl))
                pushStringAnnotation("URL", hiringUrl)
                pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))

                append("hiring page")

                pop()
                pop()
                pop()

                append(".")
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                ClickableText(
                    text = annotatedString,
                    onClick = { offset ->
                        annotatedString
                            .getStringAnnotations("URL", offset, offset).firstOrNull()
                            ?.let { annotation ->
                                localUriHandler.openUri(annotation.item)
                            }
                    },
                )
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
private fun DonateScreenPreview() {
    MaterialTheme {
        DonateStartScreen({}, {}, {}, {})
    }
}