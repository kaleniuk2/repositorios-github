package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.detail

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kaleniuk2.repositoriosgithub.R

@Composable
fun GitHubRepositoryDetailScreen(

) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        actions = {
            IconButton(onClick = { }) {
                Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "")
            }
        },
        elevation = 1.dp,
        backgroundColor = Color.Transparent
    )
}