package com.example.imagestoreapp.ui.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.KeyEvent
import android.view.MotionEvent
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

    private val historyAdapter by lazy {
        HistoryAdapter({ selectHistory ->
            // History 기록 클릭
            viewModel.setKeywordAndSaveHistory(selectHistory.keyword)
        }, { deleteHistory ->
            // History 삭제 버튼 클릭
            viewModel.deleteHistory(deleteHistory.keyword)
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initView() {
        viewDataBinding.vm = viewModel

        with(viewDataBinding.rvSearchImage) {
            adapter = pagingAdapter
        }

        with(viewDataBinding.rvSearchHistory) {
            adapter = historyAdapter
        }

        with(viewDataBinding.etSearchImage) {
            // 검색어 입력 후 돋보기 버튼 클릭
            setOnEditorActionListener(object : TextView.OnEditorActionListener {
                override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        viewModel.setKeywordAndSaveHistory(text.toString())
                        return true
                    }
                    return false
                }
            })

            // 검색어 입력하기 위해 EditText 클릭
            setOnTouchListener { _, event ->
                if (event.action == MotionEvent.ACTION_DOWN) {
                    viewModel.setIsShowHistory(true)
                }
                return@setOnTouchListener false
            }
        }

        with(viewModel) {
            // keyword가 변경되면 (= 검색을 시도하면) pagingAdapter refresh + 키보드 hide
            keyword.observe(viewLifecycleOwner, { keyword ->
                viewDataBinding.etSearchImage.setText(keyword)
                pagingAdapter.refresh()
                storeViewModel.clearStoreList()
                (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                    .hideSoftInputFromWindow(viewDataBinding.etSearchImage.windowToken, 0)
            })

            // history 리스트 변경 시 적용
            historyList.observe(viewLifecycleOwner, { keywords ->
                historyAdapter.submitList(keywords.orEmpty())
            })
        }

        lifecycleScope.launch {
            viewModel.searchPager.collectLatest { pagingData ->
                pagingAdapter.submitData(pagingData)
            }
        }
    }
}