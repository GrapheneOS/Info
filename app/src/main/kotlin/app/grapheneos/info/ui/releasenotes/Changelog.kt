package app.grapheneos.info.ui.releasenotes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.UrlAnnotation
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.grapheneos.info.ui.reusablecomposables.ClickableText
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.xml.sax.InputSource
import java.io.StringReader
import javax.xml.parsers.DocumentBuilderFactory

@Composable
fun Changelog(modifier: Modifier = Modifier, entry: String) {
    val localUriHandler = LocalUriHandler.current

    SelectionContainer {
        ElevatedCard(modifier) {
            Column(Modifier.padding(16.dp)) {
                val factory = DocumentBuilderFactory.newInstance()
                val builder = factory.newDocumentBuilder()

                val document: Document = builder.parse(InputSource(StringReader("<entry>$entry</entry>")))

                document.documentElement.normalize()

                NodeToComposable(
                    node = document.documentElement,
                    modifier = Modifier,
                    style = LocalTextStyle.current,
                    builder = AnnotatedString.Builder(),
                    localUriHandler = localUriHandler
                )
            }
        }
    }
}

@OptIn(ExperimentalTextApi::class)
@Composable
private fun NodeToComposable(
    node: Node,
    modifier: Modifier,
    style: TextStyle,
    builder: AnnotatedString.Builder,
    localUriHandler: UriHandler
) {
    val attributes = node.attributes

    var modifier = modifier

    var style = style

    // Push annotations and modify modifier and/or style
    for (a in 0 until attributes.length) {
        val attribute = attributes.item(a)

        when (attribute.nodeName) {
            "href" -> {
                builder.apply {
                    pushUrlAnnotation(UrlAnnotation(attribute.nodeValue))
                    pushStringAnnotation("URL", attribute.nodeValue)
                    pushStyle(SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold))
                }
            }

            "aria-label" -> {
                // Only VerbatimTtsAnnotation is available so we can't use the text contained for TTS
            }

            else -> {
            }
        }
    }

    ParseChildren(node, modifier, style, builder, localUriHandler)

    // Pop annotations, modifier and style don't carry over so no need to do anything for those
    for (a in 0 until attributes.length) {
        val attribute = attributes.item(a)

        when (attribute.nodeName) {
            "href" -> {
                builder.apply {
                    pop()
                    pop()
                    pop()
                }
            }

            "aria-label" -> {
            }

            else -> {
            }
        }
    }
}

@Composable
private fun ParseChildren(
    node: Node,
    modifier: Modifier,
    style: TextStyle,
    builder: AnnotatedString.Builder,
    localUriHandler: UriHandler
) {
    val children = node.childNodes

    for (i in 0 until children.length) {
        val child = children.item(i)

        when (child.nodeType) {
            Node.ELEMENT_NODE -> {
                when (child.nodeName) {
                    "title" -> {
                        val annotatedStringBuilder = AnnotatedString.Builder()

                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            annotatedStringBuilder,
                            localUriHandler
                        )

                        val annotatedString = annotatedStringBuilder.toAnnotatedString()

                        ClickableText(
                            text = annotatedString,
                            modifier,
                            onClick = { offset ->
                                annotatedString
                                    .getStringAnnotations("URL", offset, offset).firstOrNull()
                                    ?.let { annotation ->
                                        localUriHandler.openUri(annotation.item)
                                    }
                            },
                            style = typography.headlineMedium,
                        )
                    }

                    "content" -> NodeToComposable(
                        child,
                        modifier,
                        style,
                        AnnotatedString.Builder(),
                        localUriHandler
                    )

                    "div" -> NodeToComposable(
                        child,
                        modifier,
                        style,
                        AnnotatedString.Builder(),
                        localUriHandler
                    )

                    "p" -> {
                        val annotatedStringBuilder = AnnotatedString.Builder()

                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            annotatedStringBuilder,
                            localUriHandler,
                        )

                        val annotatedString = annotatedStringBuilder.toAnnotatedString()

                        val likelyHeading =
                            (annotatedString.text == "Tags:") || (annotatedString.startsWith("Changes since the"))

                        ClickableText(
                            text = annotatedString,
                            onClick = { offset ->
                                annotatedString
                                    .getStringAnnotations("URL", offset, offset).firstOrNull()
                                    ?.let { annotation ->
                                        localUriHandler.openUri(annotation.item)
                                    }
                            },
                            modifier = if (likelyHeading) {
                                modifier.padding(top = 24.dp, bottom = 16.dp)
                            } else {
                                modifier.padding(top = 24.dp)
                            },
                            style = if (likelyHeading) {
                                typography.titleLarge
                            } else {
                                style
                            },
                        )
                    }

                    "a" -> {
                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            builder,
                            localUriHandler
                        )
                    }

                    "ul" -> {
                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            AnnotatedString.Builder(),
                            localUriHandler
                        )
                    }

                    "li" -> {
                        val annotatedStringBuilder = AnnotatedString.Builder()

                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            annotatedStringBuilder,
                            localUriHandler
                        )

                        val annotatedString = annotatedStringBuilder.toAnnotatedString()

                        Row(modifier = Modifier.padding(vertical = 2.dp)) {
                            Text(
                                when (child.parentNode.nodeName) {
                                    "ul" -> "  •  "
                                    "ol" -> "  $i  "
                                    else -> ""
                                }
                            )

                            ClickableText(
                                text = annotatedString,
                                onClick = { offset ->
                                    annotatedString
                                        .getStringAnnotations("URL", offset, offset).firstOrNull()
                                        ?.let { annotation ->
                                            localUriHandler.openUri(annotation.item)
                                        }
                                }
                            )
                        }
                    }

                    "b", "strong" -> {
                        builder.withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            NodeToComposable(
                                child,
                                modifier,
                                style.copy(fontWeight = FontWeight.Bold),
                                builder,
                                localUriHandler,
                            )
                        }
                    }

                    "i", "em" -> {
                        builder.withStyle(SpanStyle(fontStyle = FontStyle.Italic)) {
                            NodeToComposable(
                                child,
                                modifier,
                                style.copy(fontStyle = FontStyle.Italic),
                                builder,
                                localUriHandler
                            )
                        }
                    }

                    "span" -> {
                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            builder,
                            localUriHandler
                        )
                    }

                    "h1", "h2", "h3", "h4", "h5", "h6" -> {
                        val annotatedStringBuilder = AnnotatedString.Builder()

                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            annotatedStringBuilder,
                            localUriHandler,
                        )

                        val annotatedString = annotatedStringBuilder.toAnnotatedString()

                        val likelyHeading =
                            (annotatedString.text == "Tags:") || (annotatedString.startsWith("Changes since the"))

                        ClickableText(
                            text = annotatedString,
                            onClick = { offset ->
                                annotatedString
                                    .getStringAnnotations("URL", offset, offset).firstOrNull()
                                    ?.let { annotation ->
                                        localUriHandler.openUri(annotation.item)
                                    }
                            },
                            modifier = if (likelyHeading) {
                                modifier.padding(top = 24.dp, bottom = 16.dp)
                            } else {
                                modifier.padding(vertical = 16.dp)
                            },
                            style = if (likelyHeading) {
                                typography.titleLarge
                            } else {
                                when (child.nodeName) {
                                    "h1" -> style.copy(
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    "h2" -> style.copy(
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    "h3" -> style.copy(
                                        fontSize = 18.72.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    "h4" -> style.copy(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    "h5" -> style.copy(
                                        fontSize = 13.28.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    "h6" -> style.copy(
                                        fontSize = 10.72.sp,
                                        fontWeight = FontWeight.Bold,
                                    )

                                    else -> style
                                }
                            },
                        )
                    }

                    else -> {
                        NodeToComposable(
                            child,
                            modifier,
                            style,
                            builder,
                            localUriHandler
                        )
                    }
                }
            }

            Node.TEXT_NODE -> {
                val textContent = child.textContent
                if (!textContent.isNullOrEmpty()) {
                    builder.apply {
                        append(textContent)
                    }
                }
            }
        }
    }
}