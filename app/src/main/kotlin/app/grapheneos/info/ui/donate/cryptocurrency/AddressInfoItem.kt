package app.grapheneos.info.ui.donate.cryptocurrency

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import app.grapheneos.info.R
import app.grapheneos.info.ui.reusablecomposables.ClickableText

@OptIn(ExperimentalTextApi::class)
@Composable
fun AddressInfoItem(
    title: String,
    qrCodePainterResourceId: Int,
    qrCodeContentDescription: String,
    addressUrl: String,
    address: String,
    showSnackbarError: (String) -> Unit,
    bottomContent: @Composable () -> Unit = {},
) {
    val localUriHandler = LocalUriHandler.current

    var showAlertDialog by rememberSaveable { mutableStateOf(false) }

    val context = LocalContext.current

    val activityNotFoundForDonationAddressSnackbarErrorMessage = stringResource(R.string.activity_not_found_for_donation_address_snackbar_error)

    ElevatedCard(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                title,
                Modifier
                    .padding(bottom = 24.dp)
                    .align(Alignment.Start),
                style = MaterialTheme.typography.titleLarge
            )
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                Icon(
                    painter = painterResource(id = qrCodePainterResourceId),
                    contentDescription = qrCodeContentDescription,
                    modifier = Modifier
                        .size(200.dp)
                        .align(Alignment.Center)
                        .clickable(
                            onClickLabel = stringResource(R.string.qr_code_on_click_label),
                            role = Role.Image
                        ) {
                            showAlertDialog = true
                        },
                    tint = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }

            val annotatedString = buildAnnotatedString {
                pushLink(LinkAnnotation.Url(addressUrl))
                pushStringAnnotation("URL", addressUrl)
                pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))

                append(address)

                pop()
                pop()
                pop()
            }

            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString
                        .getStringAnnotations("URL", offset, offset).firstOrNull()
                        ?.let { annotation ->
                            try {
                                localUriHandler.openUri(annotation.item)
                            } catch (e: ActivityNotFoundException) {
                                showSnackbarError(activityNotFoundForDonationAddressSnackbarErrorMessage)
                            }
                        }
                },
            )
            IconButton(
                onClick = {
                    var sendIntent = Intent()

                    sendIntent = sendIntent.apply {
                        action = Intent.ACTION_SEND
                        putExtra(Intent.EXTRA_TEXT, address)
                        type = "text/plain"
                    }

                    val shareIntent = Intent.createChooser(sendIntent, null)
                    ContextCompat.startActivity(context, shareIntent, ActivityOptionsCompat.makeBasic().toBundle())
                }
            ) {
                Icon(Icons.Filled.Share, contentDescription = stringResource(R.string.share))
            }
            bottomContent()
        }
        if (showAlertDialog) {
            AlertDialog(
                onDismissRequest = { showAlertDialog = false },
                confirmButton = {
                    TextButton(
                        onClick = { showAlertDialog = false }
                    ) {
                        Text(stringResource(R.string.ok))
                    }
                },
                text = {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Icon(
                            painter = painterResource(id = qrCodePainterResourceId),
                            contentDescription = stringResource(R.string.image_enlarged, qrCodeContentDescription),
                            modifier = Modifier
                                .aspectRatio(1.0f)
                                .align(Alignment.Center),
                            tint = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                }
            )
        }
    }
}