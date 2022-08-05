package com.alirezabashi98.alipool

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alirezabashi98.alipool.databinding.ActivityMarketBinding
import com.alirezabashi98.alipool.net.ApiManager
import com.alirezabashi98.alipool.net.model.NewsModel

const val TAG_MARKET = "LOG_MARKET_ACTIVITY"

class MarketActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMarketBinding
    private lateinit var dataNews: NewsModel
    private val apiManager = ApiManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMarketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()

    }

    private fun initUi() {
        getNewsFromApi()
    }

    private fun getNewsFromApi() {
        apiManager.getNews(object : ApiManager.ApiCallBack<NewsModel> {
            override fun onSuccess(data: NewsModel) {
                dataNews = data
                refreshNews()
            }

            override fun onError(errorMessage: String) {
               Log.i(TAG_MARKET,errorMessage)
            }
        })
    }

    private fun refreshNews() {
        val randomAccess = (0..(dataNews.data.size)).random()
        binding.layoutNews.txtNews.text = dataNews.data[randomAccess].title
        binding.layoutNews.txtNews.setOnClickListener { refreshNews() }
        binding.layoutNews.imgNews.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(dataNews.data[randomAccess].url)
                )
            )
        }
    }
}