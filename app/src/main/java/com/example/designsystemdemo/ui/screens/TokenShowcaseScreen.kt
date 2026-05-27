package com.example.designsystemdemo.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estebanruano.tokens.R as TokensR
import com.example.designsystemdemo.ui.theme.DesignSystemDemoTheme

// Helper to convert dimension resource (sp) to TextUnit
@Composable
private fun fontSizeResource(id: Int): TextUnit {
    val context = LocalContext.current
    val value = context.resources.getDimension(id) / context.resources.displayMetrics.scaledDensity
    return value.sp
}

@Composable
fun TokenShowcaseScreen(modifier: Modifier = Modifier) {
    // Design token colors
    val backgroundColor = colorResource(TokensR.color.color_surface_background)
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val textSecondary = colorResource(TokensR.color.color_text_secondary)

    // Design token dimensions
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)
    val spacingLg = dimensionResource(TokensR.dimen.spacing_lg)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = spacingSm, vertical = spacingSm),
        verticalArrangement = Arrangement.spacedBy(spacingMd),
    ) {
        // Header
        Text(
            text = "Design Tokens",
            color = textPrimary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_h1),
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_bold)),
            letterSpacing = (-0.02).sp
        )
        Text(
            text = "This screen showcases the design token library values including colors, spacing, typography, and dimensions.",
            color = textSecondary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body),
            lineHeight = 24.sp
        )

        Spacer(modifier = Modifier.height(spacingSm))

        // Colors Section
        SectionCard(title = "Colors") {
            ColorSubsection(
                title = "Brand Primary",
                colors = listOf(
                    TokensR.color.color_brand_primary to "Primary",
                    TokensR.color.color_brand_primary_hover to "Hover",
                    TokensR.color.color_brand_primary_light to "Light",
                    TokensR.color.color_brand_primary_tint_15 to "Tint 15%",
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_md)))

            ColorSubsection(
                title = "Surface",
                colors = listOf(
                    TokensR.color.color_surface_background to "Background",
                    TokensR.color.color_surface_surface to "Surface",
                    TokensR.color.color_surface_surface_hover to "Hover",
                    TokensR.color.color_surface_border to "Border",
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_md)))

            ColorSubsection(
                title = "Text",
                colors = listOf(
                    TokensR.color.color_text_primary to "Primary",
                    TokensR.color.color_text_secondary to "Secondary",
                    TokensR.color.color_text_tertiary to "Tertiary",
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_md)))

            ColorSubsection(
                title = "Semantic",
                colors = listOf(
                    TokensR.color.color_semantic_success to "Success",
                    TokensR.color.color_semantic_warning to "Warning",
                    TokensR.color.color_semantic_danger to "Danger",
                    TokensR.color.color_semantic_info to "Info",
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_md)))

            ColorSubsection(
                title = "Auth Gradient",
                colors = listOf(
                    TokensR.color.color_gradient_auth_gradient_color_1 to "Start",
                    TokensR.color.color_gradient_auth_gradient_color_2 to "End",
                )
            )
        }

        // Typography Section
        SectionCard(title = "Typography") {
            TypographyShowcase()
        }

        // Spacing Section
        SectionCard(title = "Spacing") {
            SpacingShowcase()
        }

        // Radius Section
        SectionCard(title = "Border Radius") {
            RadiusShowcase()
        }

        // Font Weights Section
        SectionCard(title = "Font Weights") {
            FontWeightShowcase()
        }

        // Motion Section
        SectionCard(title = "Motion") {
            MotionShowcase()
        }

        // Font Families Section
        SectionCard(title = "Font Families") {
            FontFamilyShowcase()
        }

        // Eisenhower Matrix Colors
        SectionCard(title = "Eisenhower Matrix") {
            ColorSubsection(
                title = "Urgency",
                colors = listOf(
                    TokensR.color.color_eisenhower_urgency_urgent to "Urgent",
                    TokensR.color.color_eisenhower_urgency_moderate to "Moderate",
                    TokensR.color.color_eisenhower_urgency_not to "Not Urgent",
                )
            )

            Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_md)))

            ColorSubsection(
                title = "Importance",
                colors = listOf(
                    TokensR.color.color_eisenhower_importance_high to "High",
                    TokensR.color.color_eisenhower_importance_medium to "Medium",
                    TokensR.color.color_eisenhower_importance_low to "Low",
                )
            )
        }

        Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_lg)))
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    val surfaceColor = colorResource(TokensR.color.color_surface_surface)
    val borderColor = colorResource(TokensR.color.color_surface_border)
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val radiusLg = dimensionResource(TokensR.dimen.radius_lg)
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(radiusLg),
        colors = CardDefaults.cardColors(containerColor = surfaceColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacingMd)
        ) {
            Text(
                text = title,
                color = textPrimary,
                fontSize = fontSizeResource(TokensR.dimen.font_size_h4),
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_bold))
            )
            Spacer(modifier = Modifier.height(spacingMd))
            HorizontalDivider(color = borderColor)
            Spacer(modifier = Modifier.height(spacingMd))
            content()
        }
    }
}

@Composable
private fun ColorSubsection(
    title: String,
    colors: List<Pair<Int, String>>
) {
    val textSecondary = colorResource(TokensR.color.color_text_secondary)
    val textTertiary = colorResource(TokensR.color.color_text_tertiary)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)
    val radiusMd = dimensionResource(TokensR.dimen.radius_md)

    Column {
        Text(
            text = title,
            color = textSecondary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body_sm),
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold))
        )
        Spacer(modifier = Modifier.height(spacingSm))
        Row(
            horizontalArrangement = Arrangement.spacedBy(spacingMd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            colors.forEach { (colorRes, label) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(radiusMd))
                            .background(colorResource(colorRes)),
                    )
                    Spacer(Modifier.height(dimensionResource(TokensR.dimen.spacing_xs)))
                    Text(
                        text = label,
                        color = textTertiary,
                        fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
                    )
                }
            }
        }
    }
}

@Composable
private fun TypographyShowcase() {
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(verticalArrangement = Arrangement.spacedBy(spacingSm)) {
        TypographyRow(label = "H1", fontSizeRes = TokensR.dimen.font_size_h1, sampleText = "Heading One", fontWeightRes = TokensR.integer.font_weight_bold)
        TypographyRow(label = "H2", fontSizeRes = TokensR.dimen.font_size_h2, sampleText = "Heading Two", fontWeightRes = TokensR.integer.font_weight_bold)
        TypographyRow(label = "H3", fontSizeRes = TokensR.dimen.font_size_h3, sampleText = "Heading Three", fontWeightRes = TokensR.integer.font_weight_semibold)
        TypographyRow(label = "H4", fontSizeRes = TokensR.dimen.font_size_h4, sampleText = "Heading Four", fontWeightRes = TokensR.integer.font_weight_semibold)
        TypographyRow(label = "H5", fontSizeRes = TokensR.dimen.font_size_h5, sampleText = "Heading Five", fontWeightRes = TokensR.integer.font_weight_medium)
        TypographyRow(label = "H6", fontSizeRes = TokensR.dimen.font_size_h6, sampleText = "Heading Six", fontWeightRes = TokensR.integer.font_weight_medium)
        TypographyRow(label = "Body", fontSizeRes = TokensR.dimen.font_size_body, sampleText = "Body text", fontWeightRes = TokensR.integer.font_weight_regular)
        TypographyRow(label = "Body SM", fontSizeRes = TokensR.dimen.font_size_body_sm, sampleText = "Small body", fontWeightRes = TokensR.integer.font_weight_regular)
        TypographyRow(label = "Caption", fontSizeRes = TokensR.dimen.font_size_caption, sampleText = "Caption text", fontWeightRes = TokensR.integer.font_weight_regular)
        TypographyRow(label = "Button", fontSizeRes = TokensR.dimen.font_size_button, sampleText = "BUTTON", fontWeightRes = TokensR.integer.font_weight_semibold)
        TypographyRow(label = "Mono", fontSizeRes = TokensR.dimen.font_size_mono, sampleText = "code()", fontWeightRes = TokensR.integer.font_weight_regular, fontFamily = FontFamily.Monospace)
    }
}

@Composable
private fun TypographyRow(
    label: String,
    fontSizeRes: Int,
    sampleText: String,
    fontWeightRes: Int,
    fontFamily: FontFamily = FontFamily.Default
) {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val fontSize = fontSizeResource(fontSizeRes)
    val fontWeight = FontWeight(integerResource(fontWeightRes))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label (${fontSize.value.toInt()}sp)",
            color = brandPrimaryLight,
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = sampleText,
            color = textPrimary,
            fontSize = fontSize,
            fontWeight = fontWeight,
            fontFamily = fontFamily
        )
    }
}

@Composable
private fun SpacingShowcase() {
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(verticalArrangement = Arrangement.spacedBy(spacingSm)) {
        SpacingRow(label = "XS", dimenRes = TokensR.dimen.spacing_xs)
        SpacingRow(label = "SM", dimenRes = TokensR.dimen.spacing_sm)
        SpacingRow(label = "MD", dimenRes = TokensR.dimen.spacing_md)
        SpacingRow(label = "LG", dimenRes = TokensR.dimen.spacing_lg)
        SpacingRow(label = "XL", dimenRes = TokensR.dimen.spacing_xl)
        SpacingRow(label = "XXL", dimenRes = TokensR.dimen.spacing_xxl)
    }
}

@Composable
private fun SpacingRow(
    label: String,
    dimenRes: Int
) {
    val brandPrimary = colorResource(TokensR.color.color_brand_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val size = dimensionResource(dimenRes)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label (${size.value.toInt()}dp)",
            color = brandPrimaryLight,
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            fontFamily = FontFamily.Monospace
        )
        Box(
            modifier = Modifier
                .width(size)
                .height(dimensionResource(TokensR.dimen.spacing_md))
                .background(brandPrimary, RoundedCornerShape(2.dp))
        )
    }
}

@Composable
private fun RadiusShowcase() {
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)

    Column(verticalArrangement = Arrangement.spacedBy(spacingMd)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            RadiusBox(label = "SM", dimenRes = TokensR.dimen.radius_sm)
            RadiusBox(label = "MD", dimenRes = TokensR.dimen.radius_md)
            RadiusBox(label = "LG", dimenRes = TokensR.dimen.radius_lg)
            RadiusBox(label = "XL", dimenRes = TokensR.dimen.radius_xl)
        }
    }
}

@Composable
private fun RadiusBox(
    label: String,
    dimenRes: Int
) {
    val brandPrimary = colorResource(TokensR.color.color_brand_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val radius = dimensionResource(dimenRes)

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(56.dp)
                .background(brandPrimary, RoundedCornerShape(radius))
        )
        Spacer(modifier = Modifier.height(dimensionResource(TokensR.dimen.spacing_xs)))
        Text(
            text = "$label (${radius.value.toInt()}dp)",
            color = brandPrimaryLight,
            fontSize = 10.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
private fun FontWeightShowcase() {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(verticalArrangement = Arrangement.spacedBy(spacingSm)) {
        FontWeightRow(label = "Regular", weightRes = TokensR.integer.font_weight_regular)
        FontWeightRow(label = "Medium", weightRes = TokensR.integer.font_weight_medium)
        FontWeightRow(label = "Semibold", weightRes = TokensR.integer.font_weight_semibold)
        FontWeightRow(label = "Bold", weightRes = TokensR.integer.font_weight_bold)
        FontWeightRow(label = "Extrabold", weightRes = TokensR.integer.font_weight_extrabold)
    }
}

@Composable
private fun FontWeightRow(
    label: String,
    weightRes: Int
) {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val weight = integerResource(weightRes)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$label ($weight)",
            color = brandPrimaryLight,
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "The quick brown fox",
            color = textPrimary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body),
            fontWeight = FontWeight(weight)
        )
    }
}

@Composable
private fun MotionShowcase() {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(verticalArrangement = Arrangement.spacedBy(spacingSm)) {
        MotionRow(label = "Fast", durationRes = TokensR.integer.motion_duration_fast)
        MotionRow(label = "Base", durationRes = TokensR.integer.motion_duration_base)
        MotionRow(label = "Slow", durationRes = TokensR.integer.motion_duration_slow)
    }
}

@Composable
private fun MotionRow(
    label: String,
    durationRes: Int
) {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val duration = integerResource(durationRes)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = brandPrimaryLight,
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = "${duration}ms",
            color = textPrimary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body),
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_medium))
        )
    }
}

@Composable
private fun FontFamilyShowcase() {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Column(verticalArrangement = Arrangement.spacedBy(spacingSm)) {
        FontFamilyRow(label = "Brand", familyRes = TokensR.string.font_family_brand)
        FontFamilyRow(label = "Sans", familyRes = TokensR.string.font_family_sans)
        FontFamilyRow(label = "Mono", familyRes = TokensR.string.font_family_mono)
        FontFamilyRow(label = "Mobile", familyRes = TokensR.string.font_family_mobile)
    }
}

@Composable
private fun FontFamilyRow(
    label: String,
    familyRes: Int
) {
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val brandPrimaryLight = colorResource(TokensR.color.color_brand_primary_light)
    val familyName = stringResource(familyRes)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            color = brandPrimaryLight,
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            fontFamily = FontFamily.Monospace
        )
        Text(
            text = familyName,
            color = textPrimary,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body),
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_medium))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
private fun TokenShowcaseScreenPreview() {
    DesignSystemDemoTheme {
        TokenShowcaseScreen()
    }
}
