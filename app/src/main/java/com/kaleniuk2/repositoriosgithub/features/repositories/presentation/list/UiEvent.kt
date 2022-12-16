package com.kaleniuk2.repositoriosgithub.features.repositories.presentation.list

import androidx.annotation.StringRes

sealed class UiEvent {
    data class ShowSnackBar(@StringRes val messageRes: Int): UiEvent()
}