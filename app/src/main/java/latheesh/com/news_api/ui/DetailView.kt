package latheesh.com.news_api.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import latheesh.com.news_api.R
import latheesh.com.news_api.model.Articles

class DetailView :AppCompatActivity(){

private lateinit var webView: WebView

    companion object {

        const val EXTRA_URL = "url"

        fun newIntent(context: Context,url: String): Intent {
            val detailIntent = Intent(context, DetailView::class.java)
            detailIntent.putExtra(EXTRA_URL,url )
            return detailIntent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.details_layout)

        val url = intent.extras.getString(EXTRA_URL)

        setTitle(title)

        webView = findViewById(R.id.detail_web_view)

        webView.loadUrl(url)
    }

}