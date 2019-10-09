package latheesh.com.news_api.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import latheesh.com.news_api.NewsAPIApp
import javax.inject.Singleton


@Singleton
@Component
    (modules = arrayOf(AndroidSupportInjectionModule::class,AppModule::class,ActivityModule::class,ViewModelModule::class))

public interface AppComponent
{
    @Component.Builder
    interface Builder
    {
        @BindsInstance
        fun application(application: Application):Builder
        fun build():AppComponent
    }
    fun inject(newsAPIApp: NewsAPIApp)
}
