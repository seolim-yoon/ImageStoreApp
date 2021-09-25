package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.ui.model.ThumbnailModel
import io.reactivex.Single

interface SearchRepository {
    fun getImageList(keyword: String, page: Int, size: Int): Single<List<ThumbnailModel>>

    fun getVideoList(keyword: String, page: Int, size: Int): Single<List<ThumbnailModel>>
}