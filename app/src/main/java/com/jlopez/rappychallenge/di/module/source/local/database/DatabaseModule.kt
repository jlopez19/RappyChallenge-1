package com.jlopez.rappychallenge.di.module.source.local.database

import android.content.Context
import androidx.room.Room
import com.jlopez.rappychallenge.source.local.dao.MoviePopularDao
import com.jlopez.rappychallenge.source.local.dao.VideoDao
import com.jlopez.rappychallenge.source.local.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule
{

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) : AppDatabase{
        return Room.databaseBuilder(context, AppDatabase::class.java, "movies_db").build()
    }

    @Provides
    fun provideMoviePopularDao(appDatabase: AppDatabase) : MoviePopularDao{
        return appDatabase.moviePopularDao()
    }

    @Provides
    fun provideVideoDao(appDatabase: AppDatabase) : VideoDao{
        return appDatabase.videoDao()
    }

}