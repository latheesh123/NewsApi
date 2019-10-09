package latheesh.com.news_api.adapter

import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.rowlayout.view.*
import latheesh.com.news_api.R
import latheesh.com.news_api.Utils.inflate
import latheesh.com.news_api.model.Articles
import latheesh.com.news_api.ui.DetailView

class NewsArticlesAdapter(
    private val listener: (Articles) -> Unit
) : RecyclerView.Adapter<NewsArticlesAdapter.NewsHolder>() {

    private var newsArticles: List<Articles> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NewsHolder(parent.inflate(R.layout.rowlayout))

    override fun onBindViewHolder(newsHolder: NewsHolder, position: Int) = newsHolder.bind(newsArticles[position], listener)

    override fun getItemCount() = newsArticles.size

    //ViewHolder Pattern
    class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(newsArticle: Articles, listener: (Articles) -> Unit) = with(itemView) {
            tvNewsItemTitle.text = newsArticle.title
            tvNewsAuthor.text = newsArticle.author
            tvListItemContent.text = newsArticle.content
            com.bumptech.glide.Glide.with(context)
                .load(newsArticle.urlToImage)
                .apply(
                    com.bumptech.glide.request.RequestOptions()

                        .diskCacheStrategy(com.bumptech.glide.load.engine.DiskCacheStrategy.ALL))
                .into(ivNewsImage)

            //Click Listener
            setOnClickListener {
                val selectedRecipe = newsArticle.url
                val detailIntent = selectedRecipe?.let { it1 -> DetailView.newIntent(context, it1) }
                context.startActivity(detailIntent)}


        }

    }

    /**
     * Swap function to set new data on updating
     */
    fun replaceItems(items: List<Articles>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}