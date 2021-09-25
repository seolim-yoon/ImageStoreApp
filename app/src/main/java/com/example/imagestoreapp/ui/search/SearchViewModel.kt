package com.example.imagestoreapp.ui.search

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
    private var keyword: String = ""

    val searchPager = Pager(PagingConfig(pageSize = PER_PAGE)) {
        SearchPagingSource(searchRepository, keyword)
    }.flow.cachedIn(viewModelScope)

    fun setKeyword(query: String?) {
        keyword = query ?: ""
    }

    companion object {
        const val PER_PAGE: Int = 1
    }
}