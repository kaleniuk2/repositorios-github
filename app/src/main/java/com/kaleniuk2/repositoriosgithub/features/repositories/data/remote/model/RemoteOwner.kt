package com.kaleniuk2.repositoriosgithub.features.repositories.data.remote.model

import com.google.gson.annotations.SerializedName
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model.LocalGitHubItem
import com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model.LocalOwner
import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.Owner

data class RemoteOwner(
    val login: String,
    val id: Long,

    @SerializedName("node_id")
    val nodeID: String,

    @SerializedName("avatar_url")
    val avatarURL: String,

    @SerializedName("gravatar_id")
    val gravatarID: String,

    val url: String,

    @SerializedName("html_url")
    val htmlURL: String,

    @SerializedName("followers_url")
    val followersURL: String,

    @SerializedName("following_url")
    val followingURL: String,

    @SerializedName("gists_url")
    val gistsURL: String,

    @SerializedName("starred_url")
    val starredURL: String,

    @SerializedName("subscriptions_url")
    val subscriptionsURL: String,

    @SerializedName("organizations_url")
    val organizationsURL: String,

    @SerializedName("repos_url")
    val reposURL: String,

    @SerializedName("events_url")
    val eventsURL: String,

    @SerializedName("received_events_url")
    val receivedEventsURL: String,

    @SerializedName("site_admin")
    val siteAdmin: Boolean
) {
    fun toOwner() = Owner(
        login = login,
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

    fun toDBOwner() = LocalOwner(
        login = login,
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