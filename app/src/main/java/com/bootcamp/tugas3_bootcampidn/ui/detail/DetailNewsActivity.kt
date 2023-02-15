package com.bootcamp.tugas3_bootcampidn.ui.detail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bootcamp.tugas3_bootcampidn.R
import com.bootcamp.tugas3_bootcampidn.data.response.ArticlesItem
import com.bootcamp.tugas3_bootcampidn.databinding.ActivityDetailNewsBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailNewsActivity : AppCompatActivity() {

	companion object {
		val EXTRA_NEWS = "extraNews"
	}

	private lateinit var binding: ActivityDetailNewsBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityDetailNewsBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val news = intent.getParcelableExtra<ArticlesItem>(EXTRA_NEWS)

		initView(news)

//		openWebPage(news?.url)


	}

	private fun openWebPage(url:String?) {
		val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
		startActivity(intent)
	}

	private fun initView(news : ArticlesItem?) {
		Glide.with(binding.imgNews)
			.load(news?.urlToImage)
			.apply(RequestOptions().dontTransform().placeholder(R.drawable.swy_logo))
			.into(binding.imgNews)

		binding.tvJudul.text = news?.title
		binding.tvDeskripsi.text = news?.description
		binding.btnOpenWeb.setOnClickListener {
			openWebPage(news?.url)
		}
		binding.btnBackDetail.setOnClickListener {
			finish()
		}
	}
}