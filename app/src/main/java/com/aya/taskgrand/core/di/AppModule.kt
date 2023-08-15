package com.aya.taskgrand.core.di

import android.app.Application
import androidx.room.Room
import com.aya.taskgrand.database.MatchesListDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class AppModule {


    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideDatabase(app: Application): MatchesListDatabase =
        Room.databaseBuilder(app, MatchesListDatabase::class.java, "matches_database")
            .build()

}