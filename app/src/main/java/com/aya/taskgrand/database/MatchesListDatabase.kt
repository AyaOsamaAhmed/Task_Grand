package com.aya.taskgrand.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aya.taskgrand.features.fragment.home.data.MatchDB

@Database(entities = [MatchDB::class], version = 1 , exportSchema = false)
abstract class MatchesListDatabase :RoomDatabase() {
     abstract fun matchDao(): MatchDao
}