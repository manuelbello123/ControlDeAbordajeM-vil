package com.example.controldeabordaje

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.controldeabordaje.BlockScreen.BlockScreenshots
import com.example.controldeabordaje.Navigation.AppNavigation
import com.example.controldeabordaje.ui.theme.ControlDeAbordajeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ControlDeAbordajeTheme {
                BlockScreenshots()
                AppNavigation()
            }
        }
    }
}
