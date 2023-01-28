package com.seiki.android.wallpaperapptest.retrofit.`interface`

import com.seiki.android.wallpaperapptest.model.Images
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

import retrofit2.http.Query
import retrofit2.http.Url


interface RetrofitServices {
    @GET("api/")
    fun getList(
        @Query("key") key:String,
        @Query("q") q:String,
        @Query ("image_type") image_type:String
    ): Call<Images>

    @GET
    suspend fun getImg(@Url url:String): Response<ResponseBody>
}