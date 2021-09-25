package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.data.remote.ImageStoreService
import com.example.imagestoreapp.data.remote.response.transformThumbnailModel
import com.example.imagestoreapp.di.NetworkModule.Companion.API_KEY
import com.example.imagestoreapp.ui.model.ThumbnailModel
import io.reactivex.Single
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val imageStoreService: ImageStoreService) : SearchRepository {

    override fun getImageList(keyword: String, page: Int, size: Int): Single<List<ThumbnailModel>> =
        imageStoreService.getImageList(API_KEY, keyword, "recency", page, size)
            .map { it.transformThumbnailModel() }

    override fun getVideoList(keyword: String, page: Int, size: Int): Single<List<ThumbnailModel>> =
        imageStoreService.getVideoList(API_KEY, keyword, "recency", page, size)
            .map { it.transformThumbnailModel() }

}