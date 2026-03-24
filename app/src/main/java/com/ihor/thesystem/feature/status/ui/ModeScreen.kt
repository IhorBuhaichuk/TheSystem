package com.ihor.thesystem.feature.mode.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.ihor.thesystem.core.theme.BackgroundDeep
import com.ihor.thesystem.core.theme.NeonGold
import com.ihor.thesystem.feature.mode.viewmodel.ModeViewModel

@Composable
fun ModeScreen(
    navController: NavHostController,
    viewModel: ModeViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundDeep),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "[ РЕЖИМ ЦИКЛУ ]", color = NeonGold, fontFamily = FontFamily.Monospace)
    }
}