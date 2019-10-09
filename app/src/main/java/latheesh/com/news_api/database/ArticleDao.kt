package latheesh.com.news_api.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dagger.Provides
import latheesh.com.news_api.model.Articles


@Dao
interface ArticleDao {
    @Insert
    fun insertArticles(articles: List<Articles>): List<Long>

    /**
     * Get all the articles from database
     */
    @Query("SELECT * FROM article")
    fun getNewsArticles(): LiveData<List<Articles>>
}