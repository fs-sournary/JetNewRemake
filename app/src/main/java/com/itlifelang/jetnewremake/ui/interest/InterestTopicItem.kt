package com.itlifelang.jetnewremake.ui.interest

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.itlifelang.jetnewremake.R
import com.itlifelang.jetnewremake.ui.theme.AppTheme
import com.itlifelang.jetnewremake.util.ThemePreview

@Composable
fun InterestTopicItem(
    modifier: Modifier = Modifier,
    itemTitle: String,
    selected: Boolean,
    onToggle: () -> Unit
) {
    Column(
        modifier = modifier
            .toggleable(value = selected, onValueChange = { onToggle() })
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.placeholder_1_1),
                contentDescription = null,
                modifier = Modifier
                    .size(56.dp)
                    .clip(MaterialTheme.shapes.small),
                contentScale = ContentScale.Crop
            )
            Text(
                text = itemTitle,
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                style = MaterialTheme.typography.titleMedium
            )
            SelectTopicButton(selected = selected)
        }
        Divider(
            modifier = Modifier.padding(start = 72.dp, top = 8.dp),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
        )
    }
}

@Composable
private fun SelectTopicButton(selected: Boolean) {
    val colorScheme = MaterialTheme.colorScheme
    val icon = if (selected) Icons.Filled.Done else Icons.Filled.Add
    val iconColor = if (selected) colorScheme.onPrimary else colorScheme.primary
    val borderColor =
        if (selected) colorScheme.primary else colorScheme.onSurface.copy(alpha = 0.1f)
    val backgroundColor = if (selected) colorScheme.primary else colorScheme.onPrimary
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        modifier = Modifier.size(36.dp),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Image(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.padding(8.dp),
            colorFilter = ColorFilter.tint(iconColor)
        )
    }
}

@ThemePreview
@Composable
fun PreviewSelectedInterestTopicItem() {
    AppTheme {
        Surface {
            InterestTopicItem(itemTitle = "Android", selected = true) {}
        }
    }
}

@ThemePreview
@Composable
fun PreviewUnSelectedInterestTopicItem() {
    AppTheme {
        Surface {
            InterestTopicItem(itemTitle = "Android", selected = false) {}
        }
    }
}
