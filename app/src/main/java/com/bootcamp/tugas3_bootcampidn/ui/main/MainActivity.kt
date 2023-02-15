package com.bootcamp.tugas3_bootcampidn.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bootcamp.tugas3_bootcampidn.adapter.NewsAdapter
import com.bootcamp.tugas3_bootcampidn.data.remote.ApiClient
import com.bootcamp.tugas3_bootcampidn.data.response.ArticlesItem
import com.bootcamp.tugas3_bootcampidn.data.response.NewsResponse
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityMainBinding
import com.bootcamp.tugas3_bootcampidn.ui.detail.DetailNewsActivity
import com.bootcamp.tugas3_bootcampidn.ui.detail.DetailNewsActivity.Companion.EXTRA_NEWS
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var newsAdapter: NewsAdapter

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        newsAdapter = NewsAdapter {
            moveActivity(it)
        }

        binding.rvNews.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        getNews()
    }

    private fun moveActivity(news: ArticlesItem){
        val intent = Intent(this, DetailNewsActivity::class.java)
        intent.putExtra(EXTRA_NEWS, news)
        startActivity(intent)
    }

    private fun getNews(){
        ApiClient.create().getNews().enqueue(object :Callback<NewsResponse>{
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                Log.d("MainActivity", response.body().toString())
                if(response.isSuccessful){
                    val articles = response.body()?.articles
                    newsAdapter.setNews(articles)

                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Gagal Memuat Berita",Toast.LENGTH_LONG).show()
            }

        })
    }
}