package com.jlopez.rappychallenge.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jlopez.rappychallenge.source.local.dao.MoviePopularDao
import com.jlopez.rappychallenge.source.local.dao.VideoDao
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.source.local.entity.VideoEntity


@Database(
    entities =[
        MoviePopularEntity::class,
        VideoEntity::class,
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviePopularDao() : MoviePopularDao

    abstract fun videoDao() : VideoDao

}