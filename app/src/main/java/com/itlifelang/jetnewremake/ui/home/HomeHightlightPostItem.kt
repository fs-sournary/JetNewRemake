package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.posts
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun HomeHighlightPostItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostClick: (Post) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.home_top_section_title),
            modifier = Modifier.padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleMedium
        )
        HomeHighlightPost(modifier = modifier.clickable { onPostClick(post) }, post = post)
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun HomeHighlightPost(modifier: Modifier = Modifier, post: Post) {
    val typography = MaterialTheme.typography
    val metadata = post.metadata
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = post.title,
            style = typography.titleLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = metadata.author.name,
            style = typography.labelLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = stringResource(
                id = R.string.home_post_min_read,
                formatArgs = arrayOf(metadata.date, metadata.readTimeMinutes)
            ),
            style = typography.bodySmall
        )
    }
}

@CompletePreview
@Composable
fun PreviewHomeHighlightPost() {
    AppTheme {
        Surface {
            HomeHighlightPostItem(post = posts.highlightedPost) {}
        }
    }
}
