package app.grapheneos.info.ui.community

import android.content.res.Configuration.UI_MODE_NIGHT_UNDEFINED
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
fun CommunityScreen(showSnackbarError: (String) -> Unit) {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                stringResource(R.string.chat),
                modifier = Modifier.semantics { heading() },
                style = typography.titleLarge
            )
        }
        item {
            Text(stringResource(R.string.community_screen_chat_rooms_description))
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.discord_logo),
                title = stringResource(R.string.discord),
                link = "https://discord.com/invite/grapheneos",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.telegram_logo),
                title = stringResource(R.string.telegram),
                link = "https://t.me/GrapheneOS",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.matrix_logo),
                title = stringResource(R.string.matrix),
                link = "https://matrix.to/#/%23community:grapheneos.org",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            Text(
                stringResource(R.string.forum),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .semantics { heading() },
                style = typography.titleLarge
            )
        }
        item {
            Text(stringResource(R.string.forum_description))
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.grapheneos_logo),
                title = stringResource(R.string.grapheneos_discussion_forum),
                link = "https://discuss.grapheneos.org",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            Text(
                stringResource(R.string.social_media),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .semantics { heading() },
                style = typography.titleLarge
            )
        }
        item {
            Text(
                stringResource(R.string.social_media_accounts_description)
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.x_logo),
                title = stringResource(R.string.twitter),
                link = "https://x.com/GrapheneOS",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.mastodon_logo),
                title = stringResource(R.string.mastodon),
                link = "https://grapheneos.social/@GrapheneOS",
                showSnackbarError = showSnackbarError
            )
        }
        item {
            LinkCardItem(
                painter = painterResource(id = R.drawable.bluesky_logo),
                title = stringResource(R.string.bluesky),
                link = "https://bsky.app/profile/grapheneos.org",
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
private fun CommunityScreenPreview() {
    MaterialTheme {
        CommunityScreen({})
    }
}
