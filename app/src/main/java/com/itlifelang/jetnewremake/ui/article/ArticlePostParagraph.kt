package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itlifelang.jetnewremake.model.Markup
import com.itlifelang.jetnewremake.model.MarkupType
import com.itlifelang.jetnewremake.model.Paragraph
import com.itlifelang.jetnewremake.model.ParagraphType

@Composable
fun ArticlePostParagraph(modifier: Modifier = Modifier, paragraph: Paragraph) {
    val paragraphType = paragraph.type
    val textStyle = paragraphType.getTextStyle()
    val paragraphStyle = paragraphType.getParagraphStyle()
    val trailingPadding = paragraphType.getTrailingPadding()
    val styles: List<AnnotatedString.Range<SpanStyle>> = paragraph.markups.map {
        it.toAnnotatedString(typography = MaterialTheme.typography)
    }
    val annotatedString = AnnotatedString(text = paragraph.text, spanStyles = styles)
    Box(modifier = modifier.padding(vertical = trailingPadding)) {
        when (paragraphType) {
            ParagraphType.Bullet -> BulletParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle
            )
            ParagraphType.CodeBlock -> CodeBlockParagraph(
                text = annotatedString,
                textStyle = textStyle,
                paragraphStyle = paragraphStyle
            )
            ParagraphType.Header -> Text(
                text = annotatedString,
                style = textStyle.merge(paragraphStyle)
            )
            else -> Text(text = annotatedString, style = textStyle)
        }
    }
}

@Composable
private fun ParagraphType.getTextStyle(): TextStyle {
    val typography = MaterialTheme.typography
    return when (this) {
        ParagraphType.Caption -> typography.labelMedium
        ParagraphType.Title -> typography.headlineLarge
        ParagraphType.Subhead -> typography.headlineSmall
        ParagraphType.Text -> typography.bodyLarge.copy(lineHeight = 28.sp)
        ParagraphType.Header -> typography.headlineMedium
        ParagraphType.CodeBlock -> typography.bodyLarge.copy(fontFamily = FontFamily.Monospace)
        ParagraphType.Bullet -> typography.bodyLarge
        else -> typography.bodyLarge
    }
}

@Composable
private fun ParagraphType.getParagraphStyle(): ParagraphStyle {
    return if (this == ParagraphType.Bullet) {
        ParagraphStyle(textIndent = TextIndent(firstLine = 8.sp, restLine = 8.sp))
    } else {
        ParagraphStyle()
    }
}

@Composable
private fun ParagraphType.getTrailingPadding(): Dp {
    return if (this == ParagraphType.Header ||
        this == ParagraphType.Subhead ||
        this == ParagraphType.CodeBlock
    ) {
        8.dp
    } else {
        4.dp
    }
}

@Composable
private fun Markup.toAnnotatedString(typography: Typography): AnnotatedString.Range<SpanStyle> {
    return when (type) {
        MarkupType.Link -> AnnotatedString.Range(
            item = typography.bodyLarge
                .copy(textDecoration = TextDecoration.Underline)
                .toSpanStyle(),
            start = start,
            end = end
        )
        MarkupType.Code -> AnnotatedString.Range(
            item = typography.bodyLarge
                .copy(
                    background = MaterialTheme.colorScheme.codeColorBackground,
                    fontFamily = FontFamily.Monospace
                )
                .toSpanStyle(),
            start = start,
            end = end
        )
        MarkupType.Italic -> AnnotatedString.Range(
            item = typography.bodyLarge.copy(fontStyle = FontStyle.Italic).toSpanStyle(),
            start = start,
            end = end
        )
        MarkupType.Bold -> AnnotatedString.Range(
            item = typography.bodyLarge.copy(fontWeight = FontWeight.Bold).toSpanStyle(),
            start = start,
            end = end
        )
    }
}

@Composable
private fun BulletParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle
) {
    Row {
        with(LocalDensity.current) {
            Box(
                modifier = Modifier
                    .size(8.sp.toDp())
                    .alignBy { 9.sp.roundToPx() }
                    .background(color = LocalContentColor.current, shape = CircleShape)
            )
        }
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .alignBy(FirstBaseline),
            style = textStyle.merge(paragraphStyle)
        )
    }
}

@Composable
private fun CodeBlockParagraph(
    text: AnnotatedString,
    textStyle: TextStyle,
    paragraphStyle: ParagraphStyle
) {
    Surface(
        color = MaterialTheme.colorScheme.codeColorBackground,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(16.dp),
            style = textStyle.merge(paragraphStyle)
        )
    }
}

private val ColorScheme.codeColorBackground: Color
    get() = onSurface.copy(alpha = 0.15f)
