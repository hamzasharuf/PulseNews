package com.hamzasharuf.pulse.data.api.responses


import com.google.gson.annotations.SerializedName

data class Result(
    val apiUrl: String?, // https://content.guardianapis.com/football/2020/sep/17/tottenham-hotspur-lokomotiv-ploviv-europa-league-match-report
    val fields: Fields?,
    val id: String?, // football/2020/sep/17/tottenham-hotspur-lokomotiv-ploviv-europa-league-match-report
    val isHosted: Boolean?, // false
    val pillarId: String?, // pillar/sport
    val pillarName: String?, // Sport
    val sectionId: String?, // football
    val sectionName: String?, // Football
    val tags: List<Tag>?,
    val type: String?, // article
    val webPublicationDate: String?, // 2020-09-17T18:09:04Z
    val webTitle: String?, // Tanguy Ndombele scores Tottenham winner at nine-man Lokomotiv Plovdiv
    val webUrl: String? // https://www.theguardian.com/football/2020/sep/17/tottenham-hotspur-lokomotiv-ploviv-europa-league-match-report
)