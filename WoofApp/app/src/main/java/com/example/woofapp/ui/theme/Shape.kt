package com.example.woofapp.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),   // Text fields, menus
    small      = RoundedCornerShape(8.dp),   // Chips, small buttons
    medium     = RoundedCornerShape(12.dp),  // Cards, dialogs
    large      = RoundedCornerShape(16.dp),  // FAB, navigation drawer
    extraLarge = RoundedCornerShape(28.dp)   // Bottom sheets, large FAB
)