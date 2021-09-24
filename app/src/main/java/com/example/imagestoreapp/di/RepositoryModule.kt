package com.example.imagestoreapp.di

import com.example.imagestoreapp.data.repository.search.SearchRepository
import com.example.imagestoreapp.data.repository.search.SearchRepositoryImpl
import com.example.imagestoreapp.data.repository.store.StoreRepository
import com.example.imagestoreapp.data.repository.store.StoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindSearchRepository(searchRepositoryImpl: SearchRepositoryImpl) : SearchRepository

    @Binds
    @Singleton
    fun bindStoreRepository(storeRepositoryImpl: StoreRepositoryImpl) : StoreRepository
}