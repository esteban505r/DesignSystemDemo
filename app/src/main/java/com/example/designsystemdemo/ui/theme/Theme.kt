package com.example.designsystemdemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import com.estebanruano.tokens.R

@Composable
private fun designSystemLightColorScheme() = lightColorScheme(
    primary = colorResource(R.color.color_primary_500),
    onPrimary = colorResource(R.color.color_neutral_0),
    primaryContainer = colorResource(R.color.color_primary_100),
    onPrimaryContainer = colorResource(R.color.color_primary_800),
    secondary = colorResource(R.color.color_secondary_500),
    onSecondary = colorResource(R.color.color_neutral_900),
    secondaryContainer = colorResource(R.color.color_secondary_100),
    onSecondaryContainer = colorResource(R.color.color_secondary_800),
    tertiary = colorResource(R.color.color_primary_300),
    onTertiary = colorResource(R.color.color_neutral_900),
    error = colorResource(R.color.color_semantic_error),
    onError = colorResource(R.color.color_neutral_0),
    errorContainer = colorResource(R.color.color_semantic_error_light),
    onErrorContainer = colorResource(R.color.color_semantic_error_dark),
    background = colorResource(R.color.color_neutral_50),
    onBackground = colorResource(R.color.color_neutral_900),
    surface = colorResource(R.color.color_neutral_0),
    onSurface = colorResource(R.color.color_neutral_900),
    surfaceVariant = colorResource(R.color.color_neutral_100),
    onSurfaceVariant = colorResource(R.color.color_neutral_600),
    outline = colorResource(R.color.color_neutral_300),
)

@Composable
private fun designSystemDarkColorScheme() = darkColorScheme(
    primary = colorResource(R.color.color_primary_400),
    onPrimary = colorResource(R.color.color_neutral_900),
    primaryContainer = colorResource(R.color.color_primary_700),
    onPrimaryContainer = colorResource(R.color.color_primary_100),
    secondary = colorResource(R.color.color_secondary_400),
    onSecondary = colorResource(R.color.color_neutral_900),
    secondaryContainer = colorResource(R.color.color_secondary_700),
    onSecondaryContainer = colorResource(R.color.color_secondary_100),
    tertiary = colorResource(R.color.color_primary_300),
    onTertiary = colorResource(R.color.color_neutral_900),
    error = colorResource(R.color.color_semantic_error),
    onError = colorResource(R.color.color_neutral_0),
    errorContainer = colorResource(R.color.color_semantic_error_dark),
    onErrorContainer = colorResource(R.color.color_semantic_error_light),
    background = colorResource(R.color.color_neutral_900),
    onBackground = colorResource(R.color.color_neutral_50),
    surface = colorResource(R.color.color_neutral_800),
    onSurface = colorResource(R.color.color_neutral_50),
    surfaceVariant = colorResource(R.color.color_neutral_700),
    onSurfaceVariant = colorResource(R.color.color_neutral_200),
    outline = colorResource(R.color.color_neutral_500),
)

@Composable
fun DesignSystemDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    /** When true (Android 12+), uses dynamic wallpaper colors instead of design tokens. */
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> designSystemDarkColorScheme()
        else -> designSystemLightColorScheme()
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.surface.toArgb()
            window.navigationBarColor = colorScheme.surface.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}
