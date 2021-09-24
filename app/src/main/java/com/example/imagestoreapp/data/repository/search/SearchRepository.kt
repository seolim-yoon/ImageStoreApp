package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.data.remote.response.ImageResult
import com.example.imagestoreapp.data.remote.response.VideoResult
import io.reactivex.Single

interface SearchRepository {
    fun getImageList(keyword: String, page: Int, size: Int): Single<ImageResult>

    fun getVideoList(keyword: String, page: Int, size: Int): Single<VideoResult>
}