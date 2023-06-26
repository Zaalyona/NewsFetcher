package com.example.newsfetcher.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesAdapter(private val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()
    private lateinit var itemListener : onItemClickListener

    companion object {
        var urlToImage: String = ""
        var articleTitle: String = ""
        var urlToSource: String = ""
        var articleDescription: String = ""
    }

    class ViewHolder(view: View, listener : onItemClickListener) : RecyclerView.ViewHolder(view)  {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)
        val ivNews: ImageView = view.findViewById(R.id.ivNews)

        init {
            view.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener : onItemClickListener) {
        itemListener = listener
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_article, viewGroup, false)
        return ViewHolder(view, itemListener)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        viewHolder.itemView.setOnClickListener {
            urlToImage = articlesData[position].urlToImage.toString()
            articleTitle = articlesData[position].title
            urlToSource = articlesData[position].url
            articleDescription = articlesData[position].description

            //val context = viewHolder.itemView.context
            //context.startActivity(Intent(context, ArticleActivity::class.java))

            onItemClicked.invoke(position)
        }

        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDate.text = articlesData[position].publishedAt

        Glide
            .with(viewHolder.itemView.context)
            .load(articlesData[position].urlToImage)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .placeholder(R.drawable.baseline_article_24)
            .error(R.drawable.baseline_error_24)
            .into(viewHolder.ivNews)
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }

}