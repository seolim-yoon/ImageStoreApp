package com.example.imagestoreapp.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.imagestoreapp.base.BaseViewModel
import com.example.imagestoreapp.data.repository.search.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository): BaseViewModel() {
    private var _keyword = MutableLiveData<String>()
    val keyword: LiveData<String>
        get() = _keyword

    val searchPager = Pager(PagingConfig(pageSize = PER_PAGE)) {
        SearchPagingSource(searchRepository, "hi")
    }.flow.cachedIn(viewModelScope)

    fun setKeyword(keyword: String) {
        _keyword.value = keyword
    }

    companion object {
        const val PER_PAGE: Int = 10
    }
}