package com.example.imagestoreapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.imagestoreapp.data.database.entity.History
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {
    @Query("SELECT * FROM History")
    fun getAllHistory(): Single<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History): Completable

    @Query("DELETE FROM History WHERE keyword = :keyword")
    fun deleteHistory(keyword: String): Completable
}