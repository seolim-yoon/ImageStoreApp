package com.example.imagestoreapp.ui.store

import androidx.fragment.app.viewModels
import com.example.imagestoreapp.R
import com.example.imagestoreapp.base.BaseFragment
import com.example.imagestoreapp.databinding.FragmentStoreBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StoreFragment: BaseFragment<FragmentStoreBinding, StoreViewModel>() {
    override val layoutResID: Int = R.layout.fragment_store
    override val viewModel: StoreViewModel by viewModels()

    override fun initView() {

    }
}