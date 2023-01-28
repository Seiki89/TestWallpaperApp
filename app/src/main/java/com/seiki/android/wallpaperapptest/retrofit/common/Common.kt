package com.seiki.android.wallpaperapptest.retrofit.common

import com.seiki.android.wallpaperapptest.retrofit.RetrofitClient
import com.seiki.android.wallpaperapptest.retrofit.`interface`.RetrofitServices

object Common {
    private const val BASE_URL = "https://pixabay.com/"
    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitServices::class.java)
}