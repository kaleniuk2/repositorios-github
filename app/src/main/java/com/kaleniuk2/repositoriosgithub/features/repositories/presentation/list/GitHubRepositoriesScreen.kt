package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.kaleniuk2.repositoriosgithub.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun GitHubRepositoriesScreen(
    navController: NavController,
    viewModel: GitHubRepositoriesViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.errorState.collectLatest {
            when (it) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(context.getString(it.messageRes))
                }
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.fillMaxSize()) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    item {
                        Row(Modifier.fillMaxWidth()) {
                            Text(
                                text = stringResource(id = R.string.repositories),
                                style = MaterialTheme.typography.h5,
                                overflow = TextOverflow.Ellipsis,
                                maxLines = 2,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                    items(viewModel.state.value.items) { item ->
                        GitHubItem(
                            title = item.fullName,
                            authorName = item.owner.login,
                            isPrivate = item.private,
                            forks = item.forks,
                            avatarUrl = item.owner.avatarURL,
                            description = item.description
                        ) {
                            context.startActivity(
                                Intent(
                                    ACTION_VIEW,
                                    Uri.parse(item.htmlUrl)
                                )
                            )
                        }
                    }
                }

            }
            if (viewModel.state.value.showLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Center))
            }
        }
    }
}