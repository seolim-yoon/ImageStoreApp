package com.example.imagestoreapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<DB: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {
    protected abstract val layoutResID: Int
    protected abstract val viewModel: VM

    lateinit var viewDataBinding: DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewDataBinding = DataBindingUtil.setContentView(this, layoutResID)
        viewDataBinding.lifecycleOwner = this@BaseActivity
        initView()
    }

    abstract fun initView()
}