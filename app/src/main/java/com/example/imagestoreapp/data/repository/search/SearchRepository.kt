package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.data.database.entity.History
import com.example.imagestoreapp.data.remote.response.ImageResult
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface SearchRepository {
    fun getImageList(keyword: String, page: Int, size: Int): Single<ImageResult>

    fun getTotalList(keyword: String, page: Int, size: Int) : Flowable<Any>

    fun getAllHistory() : Single<List<History>>

    fun insertHistory(history: History) : Completable

    fun deleteHistory(keyword: String) : Completable
}