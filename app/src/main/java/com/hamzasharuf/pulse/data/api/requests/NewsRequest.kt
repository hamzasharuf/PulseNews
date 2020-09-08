package com.hamzasharuf.pulse.data.api.requests


data class NewsRequest(
   val keyword: String? = null,
   val keywordInTitle: String? = null,
   val sources: String? = null,
   val domains: String? = null,
   val excludeDomains: String? = null,
   val fromDate: String? = null,
   val toDate: String? = null,
   val language: String? = null,
   val sortBy: String? = null,
   val pageSize: Int? = null,
   val page: String? = null,
): BaseRequest() {

    override fun toMap(): Map<String, String> {
        val map = HashMap<String, String>()
        if (this.keyword != null) map["q"] = keyword
        if (this.keywordInTitle != null) map["qInTitle"] = keywordInTitle
        if (this.sources != null) map["sources"] = sources
        if (this.domains != null) map["domains"] = domains
        if (this.excludeDomains != null) map["excludeDomains"] = excludeDomains
        if (this.fromDate != null) map["from"] = fromDate
        if (this.toDate != null) map["to"] = toDate
        if (this.language != null) map["language"] = language
        if (this.sortBy != null) map["sortBy"] = sortBy
        if (this.pageSize != null) map["pageSize"] = pageSize.toString()
        if (this.page != null) map["page"] = page
        return map
    }


}