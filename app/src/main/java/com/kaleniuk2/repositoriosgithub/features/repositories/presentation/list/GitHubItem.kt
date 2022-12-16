package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.kaleniuk2.repositoriosgithub.R
import com.kaleniuk2.repositoriosgithub.features.repositories.presentation.components.RoundImage

@Composable
fun GitHubItem(
    modifier: Modifier = Modifier,
    title: String,
    authorName: String,
    isPrivate: Boolean,
    forks: Int,
    avatarUrl: String,
    description: String,
    onAccessRepositoryClick: () -> Unit
) {
    Card(modifier = modifier.padding(16.dp), elevation = 2.dp) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .height(160.dp)
        ) {
            Column(
                Modifier.weight(3f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundImage(
                    rememberAsyncImagePainter(avatarUrl),
                    modifier = modifier.height(100.dp)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(text = authorName, style = MaterialTheme.typography.h6)
            }
            Column(
                modifier = Modifier
                    .weight(7f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h5,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = description, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(6.dp))
                Button(
                    border = BorderStroke(1.dp, Color.Black),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                    onClick = { onAccessRepositoryClick() }
                ) {
                    Text(stringResource(id = R.string.access_repository))
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(id = R.string.forks, forks),
                    )
                    Text(
                        text = if (isPrivate) stringResource(id = R.string.private_repository) else stringResource(
                            id = R.string.public_repository
                        ),
                    )
                }
            }
        }
    }
}