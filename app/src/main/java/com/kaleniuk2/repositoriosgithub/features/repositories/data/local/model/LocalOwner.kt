package com.kaleniuk2.repositoriosgithub.features.repositories.data.local.model

import com.kaleniuk2.repositoriosgithub.features.repositories.domain.model.Owner

data class LocalOwner(
    var login: String = "",
    var nodeID: String = "",
    var avatarURL: String = "",
    var gravatarID: String = "",
    var url: String = "",
    var htmlURL: String = "",
    var followersURL: String = "",
    var followingURL: String = "",
    var gistsURL: String = "",
    var starredURL: String = "",
    var subscriptionsURL: String = "",
    var organizationsURL: String = "",
    var reposURL: String = "",
    var eventsURL: String = "",
    var receivedEventsURL: String = "",
    var siteAdmin: Boolean = true
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
}