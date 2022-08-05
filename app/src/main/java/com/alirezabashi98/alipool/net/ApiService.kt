package com.alirezabashi98.alipool.net

import com.alirezabashi98.alipool.net.model.CoinModel
import com.alirezabashi98.alipool.net.model.NewsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiService {

    @Headers(API_KEY)
    @GET("v2/news/")
    fun getNews(@Query("sortOrder") sortOrder: String = "popular"): Call<NewsModel>

    @Headers(API_KEY)
    @GET("top/totalvolfull")
    fun getTopCoins(
        @Query("tsym") tsym: String = "USD",
        @Query("limit") limit: Int = 10,
    ): Call<CoinModel>


}