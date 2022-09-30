package com.itlifelang.jetnewremake.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun HomeRecentPostItem(
    modifier: Modifier = Modifier,
    post: Post,
    onItemClick: (Post) -> Unit,
    onMoreActionClick: (Post) -> Unit
) {
    val typography = MaterialTheme.typography
    Row(modifier = modifier.clickable { onItemClick(post) }) {
        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = null,
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
                text = stringResource(id = R.string.home_post_based_on_history),
                style = typography.labelMedium
            )
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
        IconButton(
            onClick = { onMoreActionClick(post) }
        ) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = stringResource(id = R.string.cd_more_action)
            )
        }
    }
}

@CompletePreview
@Composable
fun PreviewHomeRecentPostSection() {
    AppTheme {
        Surface {
            HomeRecentPostItem(post = post1, onItemClick = {}) {}
        }
    }
}
