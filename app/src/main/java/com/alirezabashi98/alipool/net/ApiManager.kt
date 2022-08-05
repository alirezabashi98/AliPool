package com.alirezabashi98.alipool.net

import com.alirezabashi98.alipool.net.model.CoinModel
import com.alirezabashi98.alipool.net.model.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://min-api.cryptocompare.com/data/"
const val BASE_URL_IMAGE = "https://www.cryptocompare.com"
const val API_KEY = "authorization: Apikey 6b346b5e22d85842be3329251a37c14a06b8d051bc5a5a118a23dd3d500fb448"
const val APP_NAME = "Test App"

class ApiManager {

    private val api: ApiService

    init {

        val retrofit =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        api = retrofit.create(ApiService::class.java)

    }

    fun getNews(apiCallBack: ApiCallBack<NewsModel>, sortOrder: String = "popular") {
        api.getNews(sortOrder = sortOrder).enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val body = response.body()
                if (body != null)
                    apiCallBack.onSuccess(body)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                apiCallBack.onError(t.message.toString())
            }
        })
    }

    fun getTopCoins(apiCallBack: ApiCallBack<CoinModel>, tsym: String, limit: Int) {
        api.getTopCoins(tsym = tsym, limit = limit).enqueue(object : Callback<CoinModel> {
            override fun onResponse(call: Call<CoinModel>, response: Response<CoinModel>) {
                val body = response.body()
                if (body != null)
                    apiCallBack.onSuccess(body)
            }

            override fun onFailure(call: Call<CoinModel>, t: Throwable) {
                apiCallBack.onError(t.message.toString())
            }
        })
    }

    interface ApiCallBack<T> {
        fun onSuccess(data: T)
        fun onError(errorMessage: String)
    }

}