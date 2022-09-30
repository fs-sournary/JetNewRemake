package com.itlifelang.jetnewremake.util

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * An annotation renders the light and dark theme previews of a composable.
 */
@Preview(name = "light theme", "themes")
@Preview(name = "dark theme", "themes", uiMode = UI_MODE_NIGHT_YES)
annotation class ThemePreview

/**
 * Add an annotation renders the extra small and extra large font size previews of a composable.
 */
@Preview(name = "small font", group = "font scales", fontScale = 0.5f)
@Preview(name = "large font", group = "font scales", fontScale = 1.5f)
annotation class FontScalePreview

/**
 * An annotation renders multiple device previews of a composable
 */
@Preview(name = "phone", group = "devices", device = Devices.PHONE)
@Preview(name = "tablet", group = "devices", device = Devices.TABLET)
@Preview(name = "foldable", group = "devices", device = Devices.FOLDABLE)
annotation class DevicePreview

/**
 * An annotation renders a collection of previews in common case:
 * + Theme
 * + Font scale
 * Each compose focus to a particular device type so we don't add device previews.
 * Keep it separated.
 */
@ThemePreview
@FontScalePreview
annotation class CompletePreview
