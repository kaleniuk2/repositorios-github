package com.kaleniuk2.repositoriosgithub.commons

sealed class Screen(val route: String) {
    object ListRepositories: Screen("list_repositories")
}
