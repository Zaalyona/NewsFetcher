package com.example.newsfetcher.feature.mainscreen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newsfetcher.R
import com.example.newsfetcher.feature.domain.ArticleModel

class ArticlesAdapter(val onItemClicked: (Int) -> Unit) : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {

    private var articlesData: List<ArticleModel> = emptyList()
    private lateinit var itemListener : onItemClickListener

    class ViewHolder(view: View, listener : onItemClickListener) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDate: TextView = view.findViewById(R.id.tvDate)

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
            onItemClicked.invoke(position)
        }

        viewHolder.tvTitle.text = articlesData[position].title
        viewHolder.tvDate.text = articlesData[position].publishedAt
    }

    override fun getItemCount() = articlesData.size

    fun setData(articles: List<ArticleModel>) {
        articlesData = articles
        notifyDataSetChanged()
    }
}