package com.example.imagestoreapp.ui.search

import androidx.paging.PagingState
import androidx.paging.rxjava2.RxPagingSource
import com.example.imagestoreapp.data.remote.response.ImageResult
import com.example.imagestoreapp.data.remote.response.VideoResult
import com.example.imagestoreapp.data.remote.response.transformThumbnailModel
import com.example.imagestoreapp.data.repository.search.SearchRepository
import com.example.imagestoreapp.ui.model.ThumbnailModel
import com.example.imagestoreapp.ui.search.SearchViewModel.Companion.PER_PAGE
import com.example.imagestoreapp.ui.search.SearchViewModel.Companion.VIDEO_MAX_PAGE
import io.reactivex.Single
import javax.inject.Inject

class SearchPagingSource @Inject constructor(
    private val searchRepository: SearchRepository,
    private val keyword: String
) : RxPagingSource<Int, ThumbnailModel>() {

    override fun getRefreshKey(state: PagingState<Int, ThumbnailModel>): Int? = null

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, ThumbnailModel>> {
        val nextPage = params.key ?: 1
        val list = arrayListOf<ThumbnailModel>()
        var isImageEnd = true
        var isVideoEnd = true

        // 이미지는 page 범위가 1~50이고, 비디오는 1~15이기 때문에
        // page가 15이하일 때는 이미지와 비디오를 함께 요청하고 15 초과일 때에는 이미지만 요청함
        val response = if (nextPage <= VIDEO_MAX_PAGE) {
            searchRepository.getTotalList(keyword, nextPage, PER_PAGE)
                .map { result ->
                    when (result) {
                        is ImageResult -> {
                            isImageEnd = result.meta.isEnd
                            result.transformThumbnailModel()
                        }
                        is VideoResult -> {
                            isVideoEnd = result.meta.isEnd
                            result.transformThumbnailModel()
                        }
                        else -> null
                    }
                }
                .collect(
                    { list },
                    { collectList, thumbnailModelList ->
                        thumbnailModelList?.let {
                            collectList.addAll(it)
                        }
                    }
                )
        } else {
            searchRepository.getImageList(keyword, nextPage, PER_PAGE)
                .map {
                    isImageEnd = it.meta.isEnd
                    list.addAll(it.transformThumbnailModel())
                }
        }

        return response
            .map<LoadResult<Int, ThumbnailModel>> {
                LoadResult.Page(
                    data = list.sortedByDescending { it.dateTime },
                    prevKey = null,
                    nextKey = if (list.isEmpty() || (isImageEnd && isVideoEnd)) null else nextPage + 1
                )
            }
            .onErrorReturn { e ->
                LoadResult.Error(e)
            }
    }
}