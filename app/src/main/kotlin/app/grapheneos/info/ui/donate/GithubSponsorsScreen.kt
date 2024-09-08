package app.grapheneos.info.ui.donate

import android.content.res.Configuration
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
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ClickableText
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

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
                append(stringResource(R.string.github_sponsors_description_part_1))

                append(" ")

                pushLink(LinkAnnotation.Url(githubSponsorsUrl))
                pushStringAnnotation("URL", githubSponsorsUrl)
                pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))

                append(stringResource(R.string.github_sponsors))

                pop()
                pop()
                pop()

                append(stringResource(R.string.github_sponsors_description_part_2))
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
                title = stringResource(id = R.string.github_sponsors),
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