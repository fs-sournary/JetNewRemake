package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.data.posts
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun HomePopularPostSection(
    modifier: Modifier = Modifier,
    posts: List<Post>,
    onPostItemClick: (Post) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.home_popular_section_title),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge
        )
        LazyRow(
            modifier = Modifier.padding(bottom = 16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(posts, key = { post -> post.id }) {
                PostItem(post = it, onPostItemClick = onPostItemClick)
            }
        }
        Divider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun PostItem(post: Post, onPostItemClick: (Post) -> Unit) {
    val typography = MaterialTheme.typography
    val metadata = post.metadata
    Card(
        modifier = Modifier
            .size(280.dp, 240.dp)
            .clickable { onPostItemClick(post) },
        shape = MaterialTheme.shapes.small
    ) {
        Column {
            Image(
                painter = painterResource(id = post.imageId),
                contentDescription = post.title,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = post.title,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = typography.headlineSmall
                )
                Text(
                    text = metadata.author.name,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = typography.bodyMedium
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
    }
}

@CompletePreview
@Composable
fun PreviewHomePopularPostSection() {
    AppTheme {
        Surface {
            HomePopularPostSection(posts = posts.popularPosts) {}
        }
    }
}
