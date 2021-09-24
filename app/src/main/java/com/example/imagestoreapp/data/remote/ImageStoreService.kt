package com.example.imagestoreapp.data.remote

import com.example.imagestoreapp.data.remote.response.ImageResult
import com.example.imagestoreapp.data.remote.response.VideoResult
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageStoreService {

    @GET("v2/search/image")
    fun getImageList(
        @Header("Authorization") key: String,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<ImageResult>

    @GET("v2/search/vclip")
    fun getVideoList(
        @Header("Authorization") key: String,
        @Query("query") query : String,
        @Query("sort") sort : String,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Single<VideoResult>

}