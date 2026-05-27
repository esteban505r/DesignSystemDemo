package com.example.designsystemdemo.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import com.estebanruano.tokens.R

@Composable
private fun designSystemColorScheme() = darkColorScheme(
    primary = colorResource(R.color.color_brand_primary),
    onPrimary = colorResource(R.color.color_text_primary),
    primaryContainer = colorResource(R.color.color_brand_primary_hover),
    onPrimaryContainer = colorResource(R.color.color_brand_primary_light),
    secondary = colorResource(R.color.color_semantic_info),
    onSecondary = colorResource(R.color.color_text_primary),
    secondaryContainer = colorResource(R.color.color_semantic_info_hover),
    onSecondaryContainer = colorResource(R.color.color_text_primary),
    tertiary = colorResource(R.color.color_semantic_warning),
    onTertiary = colorResource(R.color.color_text_primary),
    error = colorResource(R.color.color_semantic_danger),
    onError = colorResource(R.color.color_text_primary),
    errorContainer = colorResource(R.color.color_semantic_danger_hover),
    onErrorContainer = colorResource(R.color.color_text_primary),
    background = colorResource(R.color.color_surface_background),
    onBackground = colorResource(R.color.color_text_primary),
    surface = colorResource(R.color.color_surface_surface),
    onSurface = colorResource(R.color.color_text_primary),
    surfaceVariant = colorResource(R.color.color_surface_surface_hover),
    onSurfaceVariant = colorResource(R.color.color_text_secondary),
    outline = colorResource(R.color.color_surface_border),
)

@Composable
fun DesignSystemDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        else -> designSystemColorScheme()
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