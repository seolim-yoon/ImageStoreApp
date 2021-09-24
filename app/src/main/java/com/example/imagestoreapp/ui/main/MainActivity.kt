package com.example.imagestoreapp.ui.main

import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.imagestoreapp.R
import com.example.imagestoreapp.base.BaseActivity
import com.example.imagestoreapp.databinding.ActivityMainBinding
import com.example.imagestoreapp.ui.search.SearchFragment
import com.example.imagestoreapp.ui.store.StoreFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MainViewModel by viewModels()

    override fun initView() {
        with(viewDataBinding) {
            viewPager.adapter = PagerFragmentStateAdapter(this@MainActivity).apply {
                fragments = listOf<Fragment>(SearchFragment(), StoreFragment())
            }

            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when(position) {
                    FRAGMENT_SEARCH -> tab.text = getString(R.string.title_search)
                    FRAGMENT_STORE -> tab.text = getString(R.string.title_store)
                }
            }.attach()
        }
    }
}