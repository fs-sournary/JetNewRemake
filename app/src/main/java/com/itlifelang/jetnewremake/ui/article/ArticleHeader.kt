package com.itlifelang.jetnewremake.ui.article

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.data.post1
import com.itlifelang.jetnewremake.model.Post
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.CompletePreview

@Composable
fun ArticleHeader(modifier: Modifier = Modifier, post: Post) {
    val typography = MaterialTheme.typography
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = post.imageId),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 180.dp)
                .fillMaxWidth()
                .clip(MaterialTheme.shapes.small),
            contentScale = ContentScale.Crop
        )
        Text(
            text = post.title,
            modifier = Modifier.padding(top = 16.dp),
            style = typography.headlineMedium
        )
        if (post.subtitle.isNullOrEmpty()) {
            Text(
                text = post.subtitle!!,
                modifier = Modifier.padding(top = 8.dp),
                style = typography.bodyMedium
            )
        }
    }
}

@CompletePreview
@Composable
fun PreviewArticleHeader() {
    AppTheme {
        Surface {
            ArticleHeader(post = post1)
        }
    }
}
