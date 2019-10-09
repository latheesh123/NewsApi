package latheesh.com.news_api.ui

import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.blank_layout.*
import kotlinx.android.synthetic.main.progress_layout.*
import latheesh.com.news_api.NewsAPIApp
import latheesh.com.news_api.R
import latheesh.com.news_api.Utils.getViewModel
import latheesh.com.news_api.Utils.load
import latheesh.com.news_api.Utils.observe
import latheesh.com.news_api.Utils.toast
import latheesh.com.news_api.adapter.NewsArticlesAdapter
import latheesh.com.news_api.di.ViewModelFactory
import latheesh.com.news_api.model.Articles
import latheesh.com.news_api.viewmodel.ArticleViewModel

class MainActivity : BaseActivity() {

private val articleViewModel by lazy { getViewModel<ArticleViewModel>() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        news_list.setProgressView(progress_view)

        news_list.setEmptyView(blankview)
        val adapter = NewsArticlesAdapter {
     }


        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)

        articleViewModel.getNewsArticles().observe(this){it.load(news_list)
        { it?.let { adapter.replaceItems(it) } }}

    }
}
