package com.example.imagestoreapp.di

import android.content.Context
import com.example.imagestoreapp.data.database.AppDatabase
import com.example.imagestoreapp.data.database.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideHistoryMarkDao(appDatabase: AppDatabase): HistoryDao = appDatabase.historyDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.getInstance(context)
}