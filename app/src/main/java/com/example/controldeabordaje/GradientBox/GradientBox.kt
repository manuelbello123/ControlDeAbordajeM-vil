package com.example.controldeabordaje.GradientBox

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val blue = Color(0xFF006BC9)
val down = Color(0xFFEFFEFF)

@Composable
fun GradientBox(
    modifier: Modifier = Modifier,
    content : @Composable BoxScope.() -> Unit
){
    Box(
        modifier = Modifier
            .background(brush = Brush.linearGradient(
                listOf(
                    blue, down
                )
            ))
    ){
        content()
    }
}