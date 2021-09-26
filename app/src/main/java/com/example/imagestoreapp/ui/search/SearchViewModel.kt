package com.example.imagestoreapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.imagestoreapp.base.BaseViewModel
import com.example.imagestoreapp.data.database.entity.History
import com.example.imagestoreapp.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : BaseViewModel() {
    private var _keyword = MutableLiveData<String>()
    val keyword: LiveData<String>
        get() = _keyword

    private var _historyList = MutableLiveData<List<History>>()
    val historyList: LiveData<List<History>>
        get() = _historyList

    private var _isShowHistory = MutableLiveData<Boolean>()
    val isShowHistory: LiveData<Boolean>
        get() = _isShowHistory

    val searchPager = Pager(PagingConfig(pageSize = PER_PAGE)) {
        SearchPagingSource(searchRepository, _keyword.value ?: "")
    }.flow.cachedIn(viewModelScope)

    init {
        setIsShowHistory(false)
    }

    fun setKeywordAndSaveHistory(keyword: String) {
        _keyword.value = keyword
        setIsShowHistory(false)
        insertHistory(History(keyword))
    }

    fun setIsShowHistory(state: Boolean) {
        _isShowHistory.value = state
        if (state) {
            getAllHistory()
        }
    }

    private fun getAllHistory() {
        addDisposable(
            searchRepository.getAllHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { historys ->
                    _historyList.value = historys.reversed()
                }
        )
    }

    private fun insertHistory(historyModel: History) {
        addDisposable(
            searchRepository.insertHistory(historyModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }
        )
    }

    fun deleteHistory(keyword: String) {
        addDisposable(
            searchRepository.deleteHistory(keyword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    getAllHistory()
                }
        )
    }

    companion object {
        const val PER_PAGE: Int = 10
        const val VIDEO_MAX_PAGE: Int = 15
    }
}