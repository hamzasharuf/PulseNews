package com.hamzasharuf.pulse.data.api.responses.source

data class SourcesResponse(
    val sources: List<SourceInfoResponse>?,
    val status: String? // ok
)
