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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.LinkCardItem
import app.grapheneos.info.ui.reusablecomposables.ScreenLazyColumn

@Composable
fun CommunityScreen() {
    ScreenLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        item {
            Text(
                stringResource(R.string.chat),
                style = typography.titleLarge
            )
        }
        item {
            Text(stringResource(R.string.community_screen_chat_rooms_description))
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.discord_logo),
                stringResource(R.string.discord),
                true,
                "https://discord.com/invite/grapheneos"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.telegram_logo),
                stringResource(R.string.telegram),
                true,
                "https://t.me/GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.matrix_logo),
                stringResource(R.string.matrix),
                true,
                "https://matrix.to/#/%23community:grapheneos.org"
            )
        }
        item {
            Text(
                stringResource(R.string.forum),
                modifier = Modifier.padding(top = 16.dp),
                style = typography.titleLarge
            )
        }
        item {
            Text(stringResource(R.string.forum_description))
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.grapheneos_logo),
                stringResource(R.string.grapheneos_discussion_forum),
                true,
                "https://discuss.grapheneos.org"
            )
        }
        item {
            Text(
                stringResource(R.string.social_media),
                modifier = Modifier.padding(top = 16.dp),
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
                painterResource(id = R.drawable.x_logo),
                stringResource(R.string.twitter),
                true,
                "https://twitter.com/GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.mastodon_logo),
                stringResource(R.string.mastodon),
                true,
                "https://grapheneos.social/@GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.bluesky_logo),
                stringResource(R.string.bluesky),
                true,
                "https://bsky.app/profile/grapheneos.org"
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
        CommunityScreen()
    }
}