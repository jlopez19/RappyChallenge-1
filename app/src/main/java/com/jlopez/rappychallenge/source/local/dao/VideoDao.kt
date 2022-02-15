package com.jlopez.rappychallenge.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.jlopez.rappychallenge.source.local.entity.VideoEntity

@Dao
interface VideoDao  {

    @Insert
    fun createVideo(video: VideoEntity)

    @Update
    fun updateVideo(video: VideoEntity)

    @Query("SELECT * FROM video where id = :id")
    fun getVideo(id: String): VideoEntity


    @Query("SELECT * FROM video where movie_id = :movieId LIMIT 3")
    fun getVideos(movieId: Int): LiveData<MutableList<VideoEntity>>


}