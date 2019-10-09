package latheesh.com.news_api.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import latheesh.com.news_api.api.NewsService
import latheesh.com.news_api.database.ArticleDao
import latheesh.com.news_api.database.NewsDB
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun getNewsService(): NewsService {
        return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).
            addCallAdapterFactory(AdapterFactory()).build().create(NewsService::class.java)

    }


    @Singleton
    @Provides
    fun provideDb(app: Application):  NewsDB {
        return Room.databaseBuilder(app, NewsDB::class.java, "news-db").build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: NewsDB): ArticleDao {
        return db.articlesDao()
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }
}