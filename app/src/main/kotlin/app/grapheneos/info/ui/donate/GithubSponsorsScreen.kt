package app.grapheneos.info.ui.donate

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
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
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ClickableText
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@OptIn(ExperimentalTextApi::class)
@Composable
fun GithubSponsorsScreen() {
    val githubSponsorsUrl = "https://github.com/sponsors/thestinger"

    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            val localUriHandler = LocalUriHandler.current

            val annotatedString = buildAnnotatedString {
                append("GrapheneOS can be sponsored with recurring or one-time donations via credit cards through ")

                pushUrlAnnotation(UrlAnnotation(githubSponsorsUrl))
                pushStringAnnotation("URL", githubSponsorsUrl)
                pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))

                append("GitHub Sponsors")

                pop()
                pop()
                pop()

                append(". There are standard tiers from \$5 to \$5,000 or you can donate a custom amount.")
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
                painter = painterResource(id = R.drawable.github),
                name = stringResource(id = R.string.github_sponsors),
                tint = true,
                link = githubSponsorsUrl
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
private fun GithubSponsorsScreenPreview() {
    MaterialTheme {
        GithubSponsorsScreen()
    }
}