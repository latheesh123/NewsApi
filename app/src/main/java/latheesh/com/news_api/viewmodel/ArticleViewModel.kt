package latheesh.com.news_api.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import latheesh.com.news_api.model.Articles
import latheesh.com.news_api.model.DataResource
import latheesh.com.news_api.repository.EntertainmentRepo
import javax.inject.Inject

class ArticleViewModel @Inject constructor(entertainmentRepo: EntertainmentRepo):ViewModel() {
    private var Articles: LiveData<DataResource<List<Articles>?>> = entertainmentRepo.getArticles()

    fun getNewsArticles() = Articles
}