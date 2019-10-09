package latheesh.com.news_api.repository

import androidx.lifecycle.LiveData
import latheesh.com.news_api.Utils.AppExecutors
import latheesh.com.news_api.api.NewsService
import latheesh.com.news_api.database.ArticleDao
import latheesh.com.news_api.model.Articles
import latheesh.com.news_api.model.DataResource
import latheesh.com.news_api.model.Source
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EntertainmentRepo @Inject constructor(private val articleDao:ArticleDao,private val newsService: NewsService,
                                            private val appExecutors: AppExecutors ) {

    fun getArticles(): LiveData<DataResource<List<Articles>?>> {
        return object : NetworkBoundResource<List<Articles>, Source>(appExecutors) {
            override fun saveCallResult(item: Source) {
                articleDao.insertArticles(item.articles)
            }

            override fun shouldFetch(data: List<Articles>?) = true

            override fun loadFromDb() = articleDao.getNewsArticles()

            override fun createCall() = newsService.getNewsSource()
        }.asLiveData()
    }

}