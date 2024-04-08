package app.grapheneos.info.ui.reusablecomposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun LinkCardItem(painter: Painter, name: String, tint: Boolean, link: String) {
    val localUriHandler = LocalUriHandler.current

    ElevatedCard(
        Modifier.clickable(
            onClickLabel = "open link",
            role = Role.Button,
            onClick = { localUriHandler.openUri(link) }
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = if (tint) {
                    LocalContentColor.current
                } else {
                    Color.Unspecified
                }
            )
            Text(name, Modifier.padding(horizontal = 16.dp))
        }
    }
}

@Composable
fun LinkCardItem(imageVector: ImageVector, name: String, tint: Boolean, link: String) {
    val localUriHandler = LocalUriHandler.current

    ElevatedCard(
        Modifier.clickable(
            onClickLabel = "open link",
            role = Role.Button,
            onClick = { localUriHandler.openUri(link) }
        )
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = if (tint) {
                    LocalContentColor.current
                } else {
                    Color.Unspecified
                }
            )
            Text(name, Modifier.padding(horizontal = 16.dp))
        }
    }
}
