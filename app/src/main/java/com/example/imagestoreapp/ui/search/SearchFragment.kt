package com.example.imagestoreapp.ui.search

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imagestoreapp.R
import com.example.imagestoreapp.base.BaseFragment
import com.example.imagestoreapp.databinding.FragmentSearchBinding
import com.example.imagestoreapp.ui.store.StoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    override val layoutResID: Int = R.layout.fragment_search
    override val viewModel: SearchViewModel by viewModels()
    private val storeViewModel: StoreViewModel by activityViewModels()

    private val pagingAdapter by lazy {
        SearchPagingAdapter() { thumbnailModel ->
            storeViewModel.updateStoreList(thumbnailModel, thumbnailModel.isStore)
        }
    }

    override fun initView() {
        with(viewDataBinding.rvSearchImage) {
            adapter = pagingAdapter
        }

        // 검색어 입력후 검색 버튼 누르면 refresh + 키보드 hide
        with(viewDataBinding.etSearchImage) {
            setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        viewModel.setKeyword(text.toString())
                        pagingAdapter.refresh()
                        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(windowToken, 0)
                        return true
                    }
                    return false
                }
            })
        }

        lifecycleScope.launch {
            viewModel.searchPager.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}