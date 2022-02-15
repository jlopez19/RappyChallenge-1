package com.jlopez.rappychallenge.repository.home

import androidx.lifecycle.LiveData
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity


interface HomeRepository {
    suspend fun getPopularList()
    suspend fun getPopularListLocal(): LiveData<MutableList<MoviePopularEntity>>
    suspend fun getTopList()
    suspend fun getTopListLocal(): LiveData<MutableList<MoviePopularEntity>>
    suspend fun searchMovie(newText: String)
    fun searchMovieLocal(query: String): LiveData<MutableList<MoviePopularEntity>>
}