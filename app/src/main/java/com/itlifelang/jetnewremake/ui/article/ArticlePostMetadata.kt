package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.post1
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun ArticlePostMetadata(modifier: Modifier = Modifier, post: Post) {
    val metadata = post.metadata
    Row(
        // Merge semantics so accessibility services consider this row a single element
        modifier = modifier.semantics(mergeDescendants = true) {},
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.Filled.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(40.dp),
            colorFilter = ColorFilter.tint(LocalContentColor.current),
            contentScale = ContentScale.Fit
        )
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = metadata.author.name,
                style = MaterialTheme.typography.labelLarge
            )
            Text(
                text = stringResource(
                    id = R.string.article_post_min_read,
                    formatArgs = arrayOf(metadata.date, metadata.readTimeMinutes)
                ),
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@CompletePreview
@Composable
fun PreviewArticlePostMeta() {
    AppTheme {
        Surface {
            ArticlePostMetadata(post = post1)
        }
    }
}
