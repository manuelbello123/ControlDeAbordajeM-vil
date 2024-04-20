package com.example.controldeabordaje.BlockScreen

import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun BlockScreenshots() {
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val activity = context as? ComponentActivity
        activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_SECURE)

        onDispose {
            activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
        }
    }
}