package com.sunil0354.demo.repository.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sunil0354.demo.repository.model.TestListItem

@Dao
interface MainDao {

    /**
     * Insert data into the database
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(articles: TestListItem)

    /**
     * Get all the data from database
     */
    @Query("SELECT * FROM testlistitem_cart")
    fun getNewsData(): LiveData<List<TestListItem>>

    @Query("DELETE FROM testlistitem_cart")
    fun deleteAllData()

    @Query("DELETE FROM testlistitem_cart WHERE itemId = :id")
    fun deleteById(id: String)
}