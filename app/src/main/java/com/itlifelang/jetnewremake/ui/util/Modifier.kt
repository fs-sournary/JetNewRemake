package com.itlifelang.jetnewremake.ui.util

import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type

/**
 * Intercept a key event rather than passing it to [Modifier.onPreviewKeyEvent]'s child.
 */
fun Modifier.interceptKey(key: Key, onKeyEvent: () -> Unit): Modifier {
    return onPreviewKeyEvent {
        if (it.key == key && it.type == KeyEventType.KeyUp) {
            // Fire onKeyEvent on KeyUp to prevent duplicates
            onKeyEvent()
            true
        } else {
            // Only pass the key event to onPreviewKeyEvent's child if it is not chosen key
            it.key == key
        }
    }
}
