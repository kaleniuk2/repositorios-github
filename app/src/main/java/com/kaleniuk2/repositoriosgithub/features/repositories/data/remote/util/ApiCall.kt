package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.util

suspend fun<T> apiCall(call: suspend () -> T): Result<T> {
    return try {
        Result.success(call.invoke())
    } catch (exception: Exception) {
        Result.failure(exception)
    }
}