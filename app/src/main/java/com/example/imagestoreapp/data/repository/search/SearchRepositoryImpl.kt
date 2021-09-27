package com.example.imagestoreapp.data.repository.search

import com.example.imagestoreapp.data.database.dao.HistoryDao
import com.example.imagestoreapp.data.database.entity.History
import com.example.imagestoreapp.data.remote.ImageStoreService
import com.example.imagestoreapp.data.remote.response.ImageResult
import com.example.imagestoreapp.di.NetworkModule.Companion.API_KEY
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val imageStoreService: ImageStoreService,
    private val historyDao: HistoryDao
) : SearchRepository {

    override fun getImageList(keyword: String, page: Int, size: Int): Single<ImageResult> =
        imageStoreService.getImageList(API_KEY, keyword, "recency", page, size).subscribeOn(Schedulers.io())

    override fun getTotalList(keyword: String, page: Int, size: Int) : Flowable<Any> =
        Single.merge(
            imageStoreService.getImageList(API_KEY, keyword, "recency", page, size).subscribeOn(Schedulers.io()),
            imageStoreService.getVideoList(API_KEY, keyword, "recency", page, size).subscribeOn(Schedulers.io())
        )

    override fun getAllHistory() : Single<List<History>> =
        historyDao.getAllHistory()

    override fun insertHistory(history: History) : Completable =
        historyDao.insertHistory(history)

    override fun deleteHistory(keyword: String) : Completable =
        historyDao.deleteHistory(keyword)

}