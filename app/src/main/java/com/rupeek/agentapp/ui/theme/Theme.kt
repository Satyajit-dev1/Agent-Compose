package com.rupeek.agentapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = Orange40,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,

    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,

    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,

    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,

    background = backgroundLight,
    onBackground = onBackgroundLight,

    surface = surfaceLight,
    onSurface = onSurfaceLight,

    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,

    outline = outlineLight,
    outlineVariant = outlineVariantLight,

    inversePrimary = inversePrimaryLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,

    scrim = scrimLight
)

private val DarkColorScheme = darkColorScheme(
    primary = Orange80, // Light Orange for dark background
    onPrimary = Color(0xFF3E1F00), // Custom dark brown-orange

    primaryContainer = OrangeGrey40, // Brighter orange
    onPrimaryContainer = Orange80, // High-contrast light orange

    secondary = OrangeGrey80,
    onSecondary = Color(0xFF3E1F00),

    secondaryContainer = Peach40,
    onSecondaryContainer = OrangeGrey80,

    tertiary = Peach80,
    onTertiary = Color(0xFF3E1C00),

    tertiaryContainer = Peach40,
    onTertiaryContainer = Peach80,

    error = errorLight, // From your palette
    onError = onErrorLight,

    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,

    background = Color(0xFF121212), // Standard dark background
    onBackground = backgroundLight, // Light for contrast

    surface = Color(0xFF1E1E1E), // Typical surface tone for dark themes
    onSurface = Orange80,

    surfaceVariant = Color(0xFF2C2C2C), // Slightly lighter for components
    onSurfaceVariant = OrangeGrey80,

    outline = outlineLight,
    outlineVariant = outlineVariantLight,

    scrim = scrimLight,

    inverseSurface = OrangeGrey40,
    inverseOnSurface = Color(0xFF1A1A1A), // Very dark for contrast
    inversePrimary = Orange40
)



@Composable
fun AgentAppComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}