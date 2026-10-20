package app.grapheneos.info.ui.about

import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun AboutScreen(
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
            Text(stringResource(R.string.about_start_info_part_1))
        }
        item {
            Text(stringResource(R.string.about_start_info_part_2))
        }
        item {
            Text(stringResource(R.string.about_start_info_part_3))
        }
        item {
            Text(
                stringResource(R.string.articles),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .semantics { heading() },
                style = typography.titleLarge
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.features_overview),
                title = stringResource(R.string.features_overview),
                link = "https://grapheneos.org/features",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.install),
                title = stringResource(R.string.install),
                link = "https://grapheneos.org/install",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.build),
                title = stringResource(R.string.build),
                link = "https://grapheneos.org/build",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.usage_guide),
                title = stringResource(R.string.usage_guide),
                link = "https://grapheneos.org/usage",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.frequently_asked_questions),
                title = stringResource(R.string.frequently_asked_questions),
                link = "https://grapheneos.org/faq",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.source_code),
                title = stringResource(R.string.source_code),
                link = "https://grapheneos.org/source",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.history),
                title = stringResource(R.string.history),
                link = "https://grapheneos.org/history",
                showSnackbarError = showSnackbarError
            )
        }
    }
}

@Preview(
    showBackground = true,
    wallpaper = Wallpapers.RED_DOMINATED_EXAMPLE,
    uiMode = UI_MODE_NIGHT_UNDEFINED
)
@Composable
private fun AboutScreenPreview() {
    MaterialTheme {
        AboutScreen()
    }
}