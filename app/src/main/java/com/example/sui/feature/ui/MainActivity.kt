package com.example.sui.feature.ui

import com.example.sui.feature.data.util.ExcelImporter
import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.sui.feature.ui.theme.SuiTheme
import dagger.hilt.android.AndroidEntryPoint
import java.lang.reflect.Modifier

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        ExcelImporter(this).importData()
        setContent {
            SuiTheme {
                Navigation()
            }
        }
    }
}

@Preview(
    showBackground = true ,
)
@Composable
fun GreetingPreview() {
    SuiTheme {
        Navigation()
    }
}