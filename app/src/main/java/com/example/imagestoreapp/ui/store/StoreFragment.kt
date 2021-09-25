package com.example.imagestoreapp.ui.store

import androidx.fragment.app.activityViewModels
import com.example.imagestoreapp.R
import com.example.imagestoreapp.base.BaseFragment
import com.example.imagestoreapp.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment: BaseFragment<FragmentStoreBinding, StoreViewModel>() {
    override val layoutResID: Int = R.layout.fragment_store
    override val viewModel: StoreViewModel by activityViewModels()

    private val storeAdapter by lazy {
        StoreListAdapter() { thumbnailModel ->
            viewModel.updateStoreList(thumbnailModel, thumbnailModel.isStore)
        }
    }

    override fun initView() {
        with(viewDataBinding.rvStoreImage) {
            adapter = storeAdapter
        }

        viewModel.storeList.observe(viewLifecycleOwner, { list ->
            storeAdapter.submitList(list)
        })
    }
}