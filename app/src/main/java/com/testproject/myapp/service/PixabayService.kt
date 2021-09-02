package com.testproject.myapp.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PixabayService {
    private val service: PixabayApi

    private val client = OkHttpClient.Builder()
        .build()

    init {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .baseUrl("https://pixabay.com")
            .build()
        service = retrofit.create(PixabayApi::class.java)
    }

    fun create(): PixabayApi {
        return service
    }
}