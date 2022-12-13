package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun GitHubRepositoriesScreen(
    navController: NavController,
    viewModel: GitHubRepositoriesViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.Red)
    ) {

    }
}