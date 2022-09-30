package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.post1
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun HomeRecommendPostItem(
    modifier: Modifier = Modifier,
    post: Post,
    onPostItemClick: (Post) -> Unit,
    onBookmarkChange: (Post, Boolean) -> Unit
) {
    val typography = MaterialTheme.typography
    Row(modifier = modifier.clickable { onPostItemClick(post) }) {
        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = post.title,
            modifier = Modifier
                .padding(16.dp)
                .size(40.dp)
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
        ) {
            Text(
                text = post.title,
                style = typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(
                    id = R.string.home_post_min_read,
                    formatArgs = arrayOf(post.metadata.author.name, post.metadata.readTimeMinutes)
                ),
                style = typography.bodyMedium
            )
        }
        IconToggleButton(
            checked = true,
            onCheckedChange = { onBookmarkChange(post, it) },
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 6.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.BookmarkBorder,
                contentDescription = null
            )
        }
    }
}

@CompletePreview
@Composable
fun PreviewHomeRecommendPostSection() {
    AppTheme {
        Surface {
            HomeRecommendPostItem(post = post1, onPostItemClick = {}) { _, _ -> }
        }
    }
}
