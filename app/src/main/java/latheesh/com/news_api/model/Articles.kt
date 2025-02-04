package latheesh.com.news_api.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
data class Articles (@PrimaryKey(autoGenerate = true) var id: Int = 0,
                     @SerializedName("author") var author: String? = null,
                     @SerializedName("title") var title: String? = null,
                     @SerializedName("description") var description: String? = null,
                     @SerializedName("url") var url: String? = null,
                     @SerializedName("urlToImage") var urlToImage: String? = null,
                     @SerializedName("publishedAt") var publishedAt: String? = null,
                     @SerializedName("content") var content: String? = null)

