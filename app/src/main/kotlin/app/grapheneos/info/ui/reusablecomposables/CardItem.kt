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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    onClickLabel: String,
    onClick: () -> Unit,
) {
    ElevatedCard(modifier) {
        Row(
            Modifier
                .fillMaxWidth()
                .clickable(
                    onClickLabel = onClickLabel,
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(50.dp),
                tint = LocalContentColor.current,
            )
            Text(
                text = title,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
        }
    }
}

@Composable
fun LinkCardItem(
    modifier: Modifier = Modifier,
    painter: Painter,
    title: String,
    onClickLabel: String = stringResource(R.string.link_card_item_on_click_label),
    link: String,
    showSnackbarError: (String) -> Unit
) {
    val localUriHandler = LocalUriHandler.current
    val openUriIllegalArguementExceptionSnackbarError =
        stringResource(R.string.link_card_item_open_uri_illegal_argument_exception_snackbar_error)

    CardItem(
        modifier = modifier,
        painter = painter,
        title = title,
        onClickLabel = onClickLabel,
    ) {
        try {
            localUriHandler.openUri(link)
        } catch (e: IllegalArgumentException) {
            showSnackbarError(
                openUriIllegalArguementExceptionSnackbarError
            )
        }
    }
}

@Composable
fun LinkCardItem(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    title: String,
    link: String,
    showSnackbarError: (String) -> Unit
) {
    LinkCardItem(
        modifier = modifier,
        painter = rememberVectorPainter(imageVector),
        title = title,
        link = link,
        showSnackbarError = showSnackbarError
    )
}

@Composable
fun ScreenNavCardItem(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.outline_newsmode),
    title: String,
    navigateTo: () -> Unit,
) {
    CardItem(
        modifier = modifier,
        painter = painter,
        title = title,
        onClickLabel = stringResource(R.string.screen_nav_card_item_on_click_label),
        onClick = navigateTo,
    )
}
