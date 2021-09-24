package com.example.imagestoreapp.ui.store

import com.example.imagestoreapp.base.BaseViewModel
import com.example.imagestoreapp.data.repository.store.StoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository): BaseViewModel() {
}