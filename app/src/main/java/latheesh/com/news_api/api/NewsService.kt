package latheesh.com.news_api.api

import androidx.lifecycle.LiveData

import latheesh.com.news_api.BuildConfig
import latheesh.com.news_api.model.DataResource
import latheesh.com.news_api.model.Source
import retrofit2.http.GET

    interface NewsService {

        //Get all list retrofit

        @GET("top-headlines?country=us&category=entertainment&apiKey=" + BuildConfig.API_KEY)
        fun getNewsSource(): LiveData<DataResource<Source>>


    }
