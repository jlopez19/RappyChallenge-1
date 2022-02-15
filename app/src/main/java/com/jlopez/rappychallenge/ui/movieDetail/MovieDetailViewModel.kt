package com.jlopez.rappychallenge.ui.movieDetail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jlopez.rappychallenge.repository.movieDetail.MovieDetailRepository
import com.jlopez.rappychallenge.source.local.entity.MoviePopularEntity
import com.jlopez.rappychallenge.source.local.entity.VideoEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieDetailRepository
) : ViewModel() {

    private lateinit var _videos : LiveData<MutableList<VideoEntity>>
    val videos : LiveData<MutableList<VideoEntity>>
        get() = _videos

    lateinit var movie: MoviePopularEntity

    fun onCreate() {
        getVideos(movie.id)
        getVideosLocal(movie.id)
    }

    fun getVideos(movieId: Int){
        viewModelScope.launch {
            try {
                repository.getVideos(movieId)
            }catch (ex: Exception){
                Log.e("Error:", ex.toString())
            }
        }
    }


    fun getVideosLocal(movieId: Int){
        viewModelScope.launch {
            try {
                _videos = repository.getVideosLocal(movieId)
            }catch (ex: Exception){
                Log.e("Error:", ex.toString())
            }
        }
    }

}