package app.grapheneos.info.ui.donate

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ClickableText
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun WiseScreen(
    modifier: Modifier = Modifier,
    showSnackbarError: (String) -> Unit = {},
    additionalContentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val wiseQuickPayUrl = "https://wise.com/pay/business/grapheneosfoundation"

    ScreenLazyColumn(
        modifier = modifier
            .fillMaxSize(),
        additionalContentPadding = additionalContentPadding
    ) {
        item {
            val localUriHandler = LocalUriHandler.current

            val annotatedString = buildAnnotatedString {
                append(stringResource(R.string.wise_description_part_1))

                pushLink(LinkAnnotation.Url(wiseQuickPayUrl))
                pushStringAnnotation("URL", wiseQuickPayUrl)
                pushStyle(
                    SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                )

                append(stringResource(R.string.wise_description_part_2))

                pop()
                pop()
                pop()

                append(stringResource(R.string.wise_description_part_3))
            }

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
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.wise_logo),
                title = stringResource(R.string.wise),
                link = wiseQuickPayUrl,
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
private fun WiseScreenPreview() {
    MaterialTheme {
        WiseScreen()
    }
}