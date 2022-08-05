package com.alirezabashi98.alipool

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alirezabashi98.alipool.databinding.ActivityCoinBinding

class CoinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCoinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCoinBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}