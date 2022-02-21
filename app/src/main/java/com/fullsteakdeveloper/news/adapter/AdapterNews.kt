package com.fullsteakdeveloper.news.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.fullsteakdeveloper.news.DetailNewsActivity
import com.fullsteakdeveloper.news.R
import com.fullsteakdeveloper.news.databinding.ItemCardBinding
import com.fullsteakdeveloper.news.models.ArticleNews

class AdapterNews(var articles: ArrayList<ArticleNews>?) : RecyclerView.Adapter<AdapterNews.NewHolder>() {
    class NewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = ItemCardBinding.bind(itemView)

        val imageNews = binding.ivNews
        val titleNews = binding.tvTitleNews
        val subTitleNews = binding.tvSubtitleNews
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return NewHolder(view)
    }

    override fun onBindViewHolder(holder: NewHolder, position: Int) {
        holder.titleNews.text = articles?.get(position)?.title
        holder.subTitleNews.text = articles?.get(position)?.description

        Glide
            .with(holder.itemView.context)
            .load( articles?.get(position)?.urlToImage)
            .error(R.mipmap.ic_launcher_round)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(24)))
            .into(holder.imageNews)

        // click to detail page
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailNewsActivity::class.java)
            intent.putExtra("author", articles?.get(position)?.author)
            intent.putExtra("content", articles?.get(position)?.content)
            intent.putExtra("description", articles?.get(position)?.description)
            intent.putExtra("publishedAt", articles?.get(position)?.publishedAt)
            intent.putExtra("title", articles?.get(position)?.title)
            intent.putExtra("url", articles?.get(position)?.url)
            intent.putExtra("urlToImage", articles?.get(position)?.urlToImage)
            holder.itemView.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return articles?.size ?: 0
    }

}