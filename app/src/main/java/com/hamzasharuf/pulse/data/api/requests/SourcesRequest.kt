package com.hamzasharuf.pulse.data.api.requests


data class SourcesRequest(
    val country: String? = null,
    val language: String? = null,
    val category: String? = null,
): BaseRequest() {

    override fun toMap(): Map<String, String> {
        val map = HashMap<String, String>()
        if (this.country != null) map["country"] = country
        if (this.language != null) map["language"] = language
        if (this.category != null) map["category"] = category
        return map
    }
}