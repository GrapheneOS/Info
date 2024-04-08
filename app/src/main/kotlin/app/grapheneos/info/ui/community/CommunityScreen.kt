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
                "Chat",
                style = typography.titleLarge
            )
        }
        item {
            Text("Our chat rooms are bridged across Discord, Telegram and Matrix so you can choose your preferred platform.")
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.discord_logo),
                "Discord",
                true,
                "https://discord.com/invite/grapheneos"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.telegram_logo),
                "Telegram",
                true,
                "https://t.me/GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.matrix_logo),
                "Matrix",
                true,
                "https://matrix.to/#/%23community:grapheneos.org"
            )
        }
        item {
            Text(
                "Forum",
                modifier = Modifier.padding(top = 16.dp),
                style = typography.titleLarge
            )
        }
        item {
            Text("We have an official forum for longer form posts, which is publicly accessible and easier to search.")
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.grapheneos_logo),
                "GrapheneOS Discussion Forum",
                true,
                "https://discuss.grapheneos.org"
            )
        }
        item {
            Text(
                "Social Media",
                modifier = Modifier.padding(top = 16.dp),
                style = typography.titleLarge
            )
        }
        item {
            Text(
                "The project's official social media accounts are used for official announcements."
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.x_logo),
                "Twitter",
                true,
                "https://twitter.com/GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.mastodon_logo),
                "Mastodon",
                true,
                "https://grapheneos.social/@GrapheneOS"
            )
        }
        item {
            LinkCardItem(
                painterResource(id = R.drawable.bluesky_logo),
                "Bluesky",
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