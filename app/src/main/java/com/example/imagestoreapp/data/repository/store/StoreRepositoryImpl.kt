package com.example.imagestoreapp.data.repository.store

import com.example.imagestoreapp.data.remote.ImageStoreService
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(private val imageStoreService: ImageStoreService): StoreRepository {

}