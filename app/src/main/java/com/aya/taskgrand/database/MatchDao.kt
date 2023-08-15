package com.aya.taskgrand.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aya.taskgrand.features.fragment.home.data.MatchDB

@Dao
interface MatchDao {

    // Kotlin flow is an asynchronous stream of values
    @Query("SELECT * FROM match")
    fun getMatches(): List<MatchDB>


    // data in the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMatches(matches: MatchDB)
}