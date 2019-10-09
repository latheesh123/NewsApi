package latheesh.com.news_api.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import latheesh.com.news_api.ui.MainActivity


@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun contributeMainactvity():MainActivity
}