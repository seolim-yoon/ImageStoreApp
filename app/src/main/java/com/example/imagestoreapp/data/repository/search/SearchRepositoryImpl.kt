package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.data.remote.ImageStoreService
import com.example.imagestoreapp.data.remote.response.ImageResult
import com.example.imagestoreapp.data.remote.response.VideoResult
import com.example.imagestoreapp.di.NetworkModule.Companion.API_KEY
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val imageStoreService: ImageStoreService): SearchRepository {
    override fun getImageList(keyword: String, page: Int, size: Int): Single<ImageResult> =
        imageStoreService.getImageList(API_KEY, keyword, "recency", page, size)

    override fun getVideoList(keyword: String, page: Int, size: Int): Single<VideoResult> =
        imageStoreService.getVideoList(API_KEY, keyword, "recency", page, size)
}