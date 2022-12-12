package com.kaleniuk2.repositoriosgithub.features.repositories.data.local

import com.google.gson.annotations.SerializedName
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.Owner

data class LocalOwner(
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
) {
    fun toOwner() = Owner(
        login = login,
        id = id,
        nodeID = nodeID,
        avatarURL = avatarURL,
        gravatarID = gravatarID,
        url = url,
        htmlURL = htmlURL,
        followersURL = followersURL,
        followingURL = followingURL,
        gistsURL = gistsURL,
        starredURL = starredURL,
        subscriptionsURL = subscriptionsURL,
        organizationsURL = organizationsURL,
        reposURL = reposURL,
        eventsURL = eventsURL,
        receivedEventsURL = receivedEventsURL,
        siteAdmin = siteAdmin
    )
}