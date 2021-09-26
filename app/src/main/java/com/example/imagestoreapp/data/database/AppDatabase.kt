package com.example.imagestoreapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.imagestoreapp.data.database.dao.HistoryDao
import com.example.imagestoreapp.data.database.entity.History

@Database(entities = [History::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: run {
                    Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "history_db"
                    )
                    .build()
                }.also { INSTANCE = it }
            }
    }
}