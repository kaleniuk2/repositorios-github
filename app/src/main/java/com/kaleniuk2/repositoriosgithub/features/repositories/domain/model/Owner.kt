package com.kaleniuk2.repositoriosgithub.features.repositories.domain.model

import com.google.gson.annotations.SerializedName

data class Owner (
    val login: String,
    val id: Long,
    val nodeID: String,
    val avatarURL: String,
    val gravatarID: String,
    val url: String,
    val htmlURL: String,
    val followersURL: String,
    val followingURL: String,
    val gistsURL: String,
    val starredURL: String,
    val subscriptionsURL: String,
    val organizationsURL: String,
    val reposURL: String,
    val eventsURL: String,
    val receivedEventsURL: String,
    val siteAdmin: Boolean
)