package com.example.imagestoreapp.ui.search

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.imagestoreapp.data.repository.search.SearchRepository
import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.example.imagestoreapp.ui.search.SearchViewModel.Companion.PER_PAGE
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val searchRepository: SearchRepository,
    private val keyword: String
) : RxPagingSource<Int, ThumbnailModel>() {

    override fun getRefreshKey(state: PagingState<Int, ThumbnailModel>): Int? =
        state.anchorPosition?.let {
            anchorPosition -> state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ThumbnailModel>> {
        val nextPage = params.key ?: 1
        val list = arrayListOf<ThumbnailModel>()

        return Single.merge(
                searchRepository.getImageList(keyword, nextPage, PER_PAGE),
                searchRepository.getVideoList(keyword, nextPage, PER_PAGE)
            )
            .subscribeOn(Schedulers.io())
            .collect(
                { list },
                { collectList, thumbnailModelList -> thumbnailModelList?.let { collectList.addAll(it) } }
            )
            .map<LoadResult<Int, ThumbnailModel>> {
                LoadResult.Page(
                    data = list,
                    prevKey = null,
                    nextKey = if (list.isEmpty()) null else nextPage + 1
                )
            }
            .onErrorReturn { e ->
                LoadResult.Error(e)
            }
    }
}