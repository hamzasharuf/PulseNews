package com.hamzasharuf.pulse.data.api.responses


import com.google.gson.annotations.SerializedName

data class Tag(
    val apiUrl: String?, // https://content.guardianapis.com/profile/nick-ames
    val bio: String?, // <p>Nick Ames is a football writer for the Guardian</p>
    val bylineImageUrl: String?, // https://uploads.guim.co.uk/2017/12/19/Nick-Ames.jpg
    val bylineLargeImageUrl: String?, // https://uploads.guim.co.uk/2017/12/19/Nick_Ames,_L.png
    val firstName: String?, // Nick
    val id: String?, // profile/nick-ames
    val lastName: String?, // Ames
    val references: List<Any>,
    val twitterHandle: String?, // NickAmes82
    val type: String?, // contributor
    val webTitle: String?, // Nick Ames
    val webUrl: String? // https://www.theguardian.com/profile/nick-ames
)