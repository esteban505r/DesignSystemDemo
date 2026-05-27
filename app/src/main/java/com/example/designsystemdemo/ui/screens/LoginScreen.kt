package com.example.designsystemdemo.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.estebanruano.tokens.R as TokensR
import com.example.designsystemdemo.ui.theme.DesignSystemDemoTheme
import kotlinx.coroutines.delay

// Hardcoded demo credentials
private const val DEMO_EMAIL = "demo@oter.app"
private const val DEMO_PASSWORD = "password123"

// Helper to convert dimension resource (sp) to TextUnit
@Composable
private fun fontSizeResource(id: Int): TextUnit {
    val context = LocalContext.current
    val value = context.resources.getDimension(id) / context.resources.displayMetrics.scaledDensity
    return value.sp
}

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var email by remember { mutableStateOf(DEMO_EMAIL) }
    var password by remember { mutableStateOf(DEMO_PASSWORD) }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val gradientStart = colorResource(TokensR.color.color_gradient_auth_gradient_color_1)
    val gradientEnd = colorResource(TokensR.color.color_gradient_auth_gradient_color_2)
    val surfaceBackground = colorResource(TokensR.color.color_surface_background)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(surfaceBackground)
    ) {
        // Hero Section with gradient
        HeroSection(
            gradientStart = gradientStart,
            gradientEnd = gradientEnd,
            modifier = Modifier.fillMaxWidth()
        )

        // Sheet Section with form
        SheetSection(
            email = email,
            onEmailChange = {
                email = it
                errorMessage = null
            },
            password = password,
            onPasswordChange = {
                password = it
                errorMessage = null
            },
            passwordVisible = passwordVisible,
            onPasswordVisibilityToggle = { passwordVisible = !passwordVisible },
            rememberMe = rememberMe,
            onRememberMeChange = { rememberMe = it },
            isLoading = isLoading,
            errorMessage = errorMessage,
            onLoginClick = {
                isLoading = true
                errorMessage = null
            },
            onLoginResult = { success ->
                isLoading = false
                if (success) {
                    onLoginSuccess()
                } else {
                    errorMessage = "Invalid email or password"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        )
    }
}

@Composable
private fun HeroSection(
    gradientStart: Color,
    gradientEnd: Color,
    modifier: Modifier = Modifier,
) {
    val textSecondary = Color.White.copy(alpha = 0.85f)
    val spacingLg = dimensionResource(TokensR.dimen.spacing_lg)
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)
    val radiusMd = dimensionResource(TokensR.dimen.radius_md)

    Box(
        modifier = modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(gradientStart, gradientEnd)
                )
            )
            .padding(top = 60.dp, bottom = 40.dp, start = spacingLg, end = spacingLg)
    ) {
        // Decorative bubbles
        Box(
            modifier = Modifier
                .size(220.dp)
                .offset(x = 140.dp, y = (-60).dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.15f),
                            Color.Transparent
                        )
                    )
                )
        )
        Box(
            modifier = Modifier
                .size(140.dp)
                .offset(x = (-40).dp, y = 80.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.12f),
                            Color.Transparent
                        )
                    )
                )
        )

        Column {
            // Brand row
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Brand mark placeholder
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(RoundedCornerShape(radiusMd))
                        .background(Color.White.copy(alpha = 0.12f))
                        .border(1.dp, Color.White.copy(alpha = 0.2f), RoundedCornerShape(radiusMd)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "O",
                        color = Color.White,
                        fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_bold)),
                        fontSize = fontSizeResource(TokensR.dimen.font_size_h4)
                    )
                }
                Text(
                    text = "Oter",
                    color = Color.White,
                    fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_bold)),
                    fontSize = fontSizeResource(TokensR.dimen.font_size_h4),
                    letterSpacing = (-0.01).sp
                )
            }

            Spacer(modifier = Modifier.height(56.dp))

            // Eyebrow pill
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(dimensionResource(TokensR.dimen.radius_full)))
                    .background(Color.White.copy(alpha = 0.12f))
                    .border(1.dp, Color.White.copy(alpha = 0.18f), RoundedCornerShape(dimensionResource(TokensR.dimen.radius_full)))
                    .padding(horizontal = 10.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "WELCOME BACK",
                    color = Color.White.copy(alpha = 0.75f),
                    fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold)),
                    fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
                    letterSpacing = 0.12.sp
                )
            }

            Spacer(modifier = Modifier.height(spacingMd))

            // Hero title
            Text(
                text = "Sign in to\nyour account",
                color = Color.White,
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_extrabold)),
                fontSize = fontSizeResource(TokensR.dimen.font_size_h1),
                lineHeight = 36.sp,
                letterSpacing = (-0.02).sp
            )

            Spacer(modifier = Modifier.height(spacingSm))

            // Subtitle
            Text(
                text = "Manage your life in one place.",
                color = textSecondary,
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_regular)),
                fontSize = fontSizeResource(TokensR.dimen.font_size_body),
                lineHeight = 22.sp
            )
        }
    }
}

@Composable
private fun SheetSection(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit,
    rememberMe: Boolean,
    onRememberMeChange: (Boolean) -> Unit,
    isLoading: Boolean,
    errorMessage: String?,
    onLoginClick: () -> Unit,
    onLoginResult: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    val surfaceBackground = colorResource(TokensR.color.color_surface_background)
    val focusManager = LocalFocusManager.current
    val spacingLg = dimensionResource(TokensR.dimen.spacing_lg)
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)
    val radiusXl = dimensionResource(TokensR.dimen.radius_xl)
    val motionDurationSlow = integerResource(TokensR.integer.motion_duration_slow)

    // Handle login simulation
    LaunchedEffect(isLoading) {
        if (isLoading) {
            delay(motionDurationSlow.toLong() * 5) // Simulate network delay
            val success = email == DEMO_EMAIL && password == DEMO_PASSWORD
            onLoginResult(success)
        }
    }

    Column(
        modifier = modifier
            .offset(y = (-22).dp)
            .clip(RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(surfaceBackground)
            .padding(horizontal = spacingLg, vertical = 26.dp)
    ) {
        // Sheet grab handle
        Box(
            modifier = Modifier
                .width(44.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(2.dp))
                .background(colorResource(TokensR.color.color_surface_border_light))
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(spacingLg))

        // Error message
        AnimatedVisibility(
            visible = errorMessage != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            ErrorBanner(
                message = errorMessage ?: "",
                modifier = Modifier.padding(bottom = spacingMd)
            )
        }

        // Email field
        LoginTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "EMAIL",
            placeholder = "Enter your email",
            leadingIcon = Icons.Default.Email,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
            isError = errorMessage != null,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(spacingMd))

        // Password field
        LoginTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "PASSWORD",
            placeholder = "Enter your password",
            leadingIcon = Icons.Default.Lock,
            trailingIcon = {
                IconButton(onClick = onPasswordVisibilityToggle) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        tint = colorResource(TokensR.color.color_text_secondary)
                    )
                }
            },
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (!isLoading && email.isNotEmpty() && password.isNotEmpty()) {
                        onLoginClick()
                    }
                }
            ),
            isError = errorMessage != null,
            enabled = !isLoading
        )

        Spacer(modifier = Modifier.height(spacingSm))

        // Remember me + Forgot password row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Remember me checkbox
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(enabled = !isLoading) { onRememberMeChange(!rememberMe) }
            ) {
                Box(
                    modifier = Modifier
                        .size(20.dp)
                        .clip(RoundedCornerShape(dimensionResource(TokensR.dimen.radius_sm)))
                        .background(
                            if (rememberMe) colorResource(TokensR.color.color_brand_primary)
                            else Color.Transparent
                        )
                        .border(
                            width = 1.5.dp,
                            color = if (rememberMe) colorResource(TokensR.color.color_brand_primary)
                            else colorResource(TokensR.color.color_surface_border_light),
                            shape = RoundedCornerShape(dimensionResource(TokensR.dimen.radius_sm))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    if (rememberMe) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Remember me",
                    color = colorResource(TokensR.color.color_text_secondary),
                    fontSize = fontSizeResource(TokensR.dimen.font_size_body_sm)
                )
            }

            // Forgot password
            Text(
                text = "Forgot password?",
                color = colorResource(TokensR.color.color_brand_primary_light),
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold)),
                fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
                modifier = Modifier.clickable(enabled = !isLoading) { /* TODO */ }
            )
        }

        Spacer(modifier = Modifier.height(spacingLg))

        // Login button
        LoginButton(
            text = "Sign In",
            isLoading = isLoading,
            enabled = email.isNotEmpty() && password.isNotEmpty() && !isLoading,
            onClick = onLoginClick
        )

        Spacer(modifier = Modifier.weight(1f))

        // Sign up CTA
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Don't have an account? ",
                color = colorResource(TokensR.color.color_text_secondary),
                fontSize = fontSizeResource(TokensR.dimen.font_size_body_sm)
            )
            Text(
                text = "Sign up",
                color = colorResource(TokensR.color.color_brand_primary_light),
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold)),
                fontSize = fontSizeResource(TokensR.dimen.font_size_body_sm),
                modifier = Modifier.clickable { /* TODO */ }
            )
        }
    }
}

@Composable
private fun LoginTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isError: Boolean = false,
    enabled: Boolean = true,
) {
    val surfaceColor = colorResource(TokensR.color.color_surface_surface)
    val borderColor = if (isError) {
        colorResource(TokensR.color.color_semantic_danger)
    } else {
        colorResource(TokensR.color.color_surface_border)
    }
    val textPrimary = colorResource(TokensR.color.color_text_primary)
    val textTertiary = colorResource(TokensR.color.color_text_tertiary)
    val radiusMd = dimensionResource(TokensR.dimen.radius_md)
    val spacingXs = dimensionResource(TokensR.dimen.spacing_xs)

    Column(modifier = modifier) {
        // Label
        Text(
            text = label,
            color = textTertiary,
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold)),
            fontSize = fontSizeResource(TokensR.dimen.font_size_caption),
            letterSpacing = 0.04.sp,
            modifier = Modifier.padding(start = 2.dp, bottom = spacingXs)
        )

        // Input field
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            enabled = enabled,
            placeholder = {
                Text(
                    text = placeholder,
                    color = textTertiary
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = textTertiary,
                    modifier = Modifier.size(18.dp)
                )
            },
            trailingIcon = trailingIcon,
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            shape = RoundedCornerShape(radiusMd),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = surfaceColor,
                unfocusedContainerColor = surfaceColor,
                disabledContainerColor = surfaceColor,
                focusedBorderColor = colorResource(TokensR.color.color_brand_primary),
                unfocusedBorderColor = borderColor,
                errorBorderColor = colorResource(TokensR.color.color_semantic_danger),
                focusedTextColor = textPrimary,
                unfocusedTextColor = textPrimary,
                cursorColor = colorResource(TokensR.color.color_brand_primary_light)
            )
        )
    }
}

@Composable
private fun LoginButton(
    text: String,
    isLoading: Boolean,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val primaryColor = colorResource(TokensR.color.color_brand_primary)
    val radiusMd = dimensionResource(TokensR.dimen.radius_md)

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(radiusMd),
        colors = ButtonDefaults.buttonColors(
            containerColor = primaryColor,
            disabledContainerColor = primaryColor.copy(alpha = 0.5f)
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 4.dp
        )
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(18.dp),
                color = Color.White,
                strokeWidth = 2.5.dp
            )
        } else {
            Text(
                text = text,
                fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_semibold)),
                fontSize = fontSizeResource(TokensR.dimen.font_size_button)
            )
        }
    }
}

@Composable
private fun ErrorBanner(
    message: String,
    modifier: Modifier = Modifier,
) {
    val errorColor = colorResource(TokensR.color.color_semantic_danger)
    val radiusMd = dimensionResource(TokensR.dimen.radius_md)
    val spacingMd = dimensionResource(TokensR.dimen.spacing_md)
    val spacingSm = dimensionResource(TokensR.dimen.spacing_sm)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(radiusMd))
            .background(errorColor.copy(alpha = 0.15f))
            .border(1.dp, errorColor.copy(alpha = 0.3f), RoundedCornerShape(radiusMd))
            .padding(horizontal = spacingMd, vertical = spacingSm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            text = message,
            color = errorColor,
            fontSize = fontSizeResource(TokensR.dimen.font_size_body_sm),
            fontWeight = FontWeight(integerResource(TokensR.integer.font_weight_medium))
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF0F172A)
@Composable
private fun LoginScreenPreview() {
    DesignSystemDemoTheme {
        LoginScreen(onLoginSuccess = {})
    }
}
