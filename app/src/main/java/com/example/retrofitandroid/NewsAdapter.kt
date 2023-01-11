package com.example.retrofitandroid

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsAdapter(val context: Context, val newsList: List<Articles>)
    : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false)
        return ArticleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val article = newsList[position]
        holder.articleTitle.text = article.title
        holder.articleDescription.text = article.description
        Glide.with(context).load(article.urlToImage).into(holder.articleImage)

        /// if we want to set any listener, if anything in the entire item is pressed
        holder.root.setOnClickListener{
            Toast.makeText(context, "${article.title} pressed", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    class ArticleViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val articleImage = view.findViewById<ImageView>(R.id.imageView)
        val articleTitle = view.findViewById<TextView>(R.id.title)
        val articleDescription = view.findViewById<TextView>(R.id.description)

        val root = view
    }
}