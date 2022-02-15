package com.jlopez.rappychallenge.repository.movieDetail

import androidx.lifecycle.LiveData
import com.jlopez.rappychallenge.source.local.entity.VideoEntity

interface MovieDetailRepository {
    suspend fun getVideos(movieId: Int)
    suspend fun getVideosLocal(movieId: Int): LiveData<MutableList<VideoEntity>>
}