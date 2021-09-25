package com.example.imagestoreapp.ui.store

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imagestoreapp.base.BaseViewModel
import com.example.imagestoreapp.data.repository.store.StoreRepository
import com.example.imagestoreapp.ui.model.ThumbnailModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(private val storeRepository: StoreRepository): BaseViewModel() {
    private var _storeList = MutableLiveData<List<ThumbnailModel>>()
    val storeList: LiveData<List<ThumbnailModel>>
        get() = _storeList

    init {
        _storeList.value = arrayListOf()
    }

    fun updateStoreList(thumbnailModel: ThumbnailModel, isStore: Boolean) {
        val list = _storeList.value?.toMutableList()?.apply {
            when(isStore) {
                true -> add(thumbnailModel)
                false -> remove(thumbnailModel)
            }
        }
        _storeList.value = list ?: arrayListOf()
    }
}