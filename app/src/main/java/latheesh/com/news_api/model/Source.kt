package latheesh.com.news_api.model

import com.google.gson.annotations.SerializedName

data class Source (
    @SerializedName("status") var status:String="",
    @SerializedName("articles") var articles: List<Articles> = emptyList())
