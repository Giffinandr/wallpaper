package com.testproject.myapp.service

import com.testproject.myapp.viewmodel.PixabayImageList
import retrofit2.http.*

interface PixabayApi {

    @GET("/api/")
    suspend fun getImageResults(@Query("key") key: String,
                        @Query("q") q: String,
                        @Query("per_page") perPage: Int
                                ):
            PixabayImageList
}