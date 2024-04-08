package app.grapheneos.info.ui.reusablecomposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import app.grapheneos.info.R

@Composable
fun ScreenNavCardItem(
    painter: Painter = painterResource(id = R.drawable.outline_newsmode),
    name: String,
    navigateTo: () -> Unit
) {
    ElevatedCard(
        Modifier.clickable(
            onClickLabel = "open screen",
            role = Role.Button,
            onClick = { navigateTo() }
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
            )
            Text(name, Modifier.padding(horizontal = 16.dp))
        }
    }
}