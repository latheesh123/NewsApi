package latheesh.com.news_api.database

import androidx.room.Database
import androidx.room.RoomDatabase
import latheesh.com.news_api.model.Articles


@Database (entities = [Articles::class],version = 1)
abstract class NewsDB :RoomDatabase(){

    abstract fun articlesDao(): ArticleDao

}