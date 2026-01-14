package com.example.portugueseverbs.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// E-ink optimized color scheme - black and white only
private val EInkColorScheme = lightColorScheme(
    primary = EInkPrimary,
    onPrimary = EInkOnPrimary,
    primaryContainer = EInkPrimaryContainer,
    onPrimaryContainer = EInkOnPrimaryContainer,
    secondary = EInkSecondary,
    onSecondary = EInkOnSecondary,
    secondaryContainer = EInkSecondaryContainer,
    onSecondaryContainer = EInkOnSecondaryContainer,
    tertiary = EInkTertiary,
    onTertiary = EInkOnTertiary,
    tertiaryContainer = EInkTertiaryContainer,
    onTertiaryContainer = EInkOnTertiaryContainer,
    error = EInkError,
    onError = EInkOnError,
    errorContainer = EInkErrorContainer,
    onErrorContainer = EInkOnErrorContainer,
    background = EInkBackground,
    onBackground = EInkOnBackground,
    surface = EInkSurface,
    onSurface = EInkOnSurface,
    surfaceVariant = EInkSurfaceVariant,
    onSurfaceVariant = EInkOnSurfaceVariant,
    outline = EInkOutline,
    outlineVariant = EInkOutlineVariant
)

@Composable
fun PortugueseVerbsTheme(
    content: @Composable () -> Unit
) {
    // Always use e-ink optimized black & white theme
    val colorScheme = EInkColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = White.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = true
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = EInkTypography,
        content = content
    )
}
