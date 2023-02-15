package com.bootcamp.tugas3_bootcampidn.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.tugas3_bootcampidn.R
import com.bootcamp.tugas3_bootcampidn.data.response.ArticlesItem
import com.bootcamp.tugas3_bootcampidn.databinding.ItemRowNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class NewsAdapter(private val listener: (ArticlesItem)-> Unit) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private var news = listOf<ArticlesItem?>()

    fun setNews(news: List<ArticlesItem?>?) {
        if (news != null) {
            this.news = news
        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val rowBinding = ItemRowNewsBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(rowBinding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        return holder.bind(news[position])
    }

    inner class NewsViewHolder(private val binding: ItemRowNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: ArticlesItem?) {
            binding.apply {
                Glide.with(imgNews.context)
                    .load(news?.urlToImage)
                    .apply(RequestOptions().dontTransform().placeholder(R.drawable.swy_logo))
                    .into(imgNews)
                tvJudul.text = news?.title
                if(news?.author != null) {
                    tvPenulis.text = news?.author
                }else{
                    tvPenulis.text = "Anonim"
                }
                tvTanggalPosting.text = news?.publishedAt
                binding.cardNews.setOnClickListener {
                    if (news != null) {
                        listener(news)
                    }
                }
            }
        }
    }
}