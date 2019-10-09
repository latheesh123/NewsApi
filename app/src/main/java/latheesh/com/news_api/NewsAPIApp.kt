package latheesh.com.news_api

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import latheesh.com.news_api.di.AppInjector;
import javax.inject.Inject

class NewsAPIApp :Application(),HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }
    override fun activityInjector(): AndroidInjector<Activity> =dispatchingAndroidInjector


}