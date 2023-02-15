package com.bootcamp.tugas3_bootcampidn.data.remote

import com.bootcamp.tugas3_bootcampidn.data.response.NewsResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiServices {

    @GET("top-headlines?country=id&apiKey=f044de69086e45198f8406be2094a229")
    fun getNews():Call<NewsResponse>

}