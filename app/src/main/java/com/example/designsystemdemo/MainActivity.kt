package com.example.designsystemdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.designsystemdemo.ui.screens.LoginScreen
import com.example.designsystemdemo.ui.screens.TokenShowcaseScreen
import com.example.designsystemdemo.ui.theme.DesignSystemDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesignSystemDemoTheme {
                var isLoggedIn by remember { mutableStateOf(false) }

                if (isLoggedIn) {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        TokenShowcaseScreen(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                } else {
                    LoginScreen(
                        onLoginSuccess = { isLoggedIn = true },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}
